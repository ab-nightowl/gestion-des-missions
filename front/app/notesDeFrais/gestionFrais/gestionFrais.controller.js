export default class gestionFraisCtrl {
    constructor(gestionFraisService, pdfMake, detailGestionFraisService, moment) {
        this.gestionFraisService = gestionFraisService
        this.sumFraisMission()
        this.findMission()
        this.mission = []
        this.dateNow = new Date();
        this.order = "dateDebut";
        this.triInverse = false;
        this.pdfMake = pdfMake
        this.detailGestionFraisService = detailGestionFraisService
        this.moment = moment
    }

    findMission() {
        this.gestionFraisService.findMission(sessionStorage.getItem("userMatricule"))
            .then(mission => {
                mission.forEach(m => {
                    this.sumfrais.forEach(sf => {
                        if (sf.missionId === m.id) {
                            m.frais = sf.sumMontant
                        }
                        else {
                            if (m.frais == null || m.frais == 0) {
                                m.frais = 0
                            }
                        }
                    }, this);

                    this.mission.push(m)
                }, this);
            })
    }

    sumFraisMission() {
        this.gestionFraisService.sumFraisMission()
            .then(frais => {
                this.sumfrais = frais
            })
    }

    updateOrderEtTri(order) {
        this.order = order;
        this.triInverse = !this.triInverse;
    }

    buildTableBody(data, columns) {
        let body = [];

        body.push(columns);

        data.forEach(function (row) {
            let dataRow = [];

            columns.forEach(function (column) {
                dataRow.push(row[column].toString());
            })

            body.push(dataRow);
        });

        return body;
    }

    table(data, columns) {
        return {
            table: {
                headerRows: 1,
                widths: ['*', '*', '*'],
                body: this.buildTableBody(data, columns)
            }
        };
    }

    findDetailsMission(idMission) {
        this.detailGestionFraisService.findMission(idMission)
            .then(dm => {
                this.detailMission = dm
            })
            .then(() => {
                this.findFraisMission(idMission)
            })
    }

    findFraisMission(idMission) {
        this.detailGestionFraisService.findFraisMission(idMission)
            .then(fm => {
                this.fraisMission = fm
            })
            .then(() => {
                this.creerPdf()
            })

    }

    pdf(idMission) {
        this.findDetailsMission(idMission)
    }

    creerPdf() {
        this.frais = []
        this.fraisMission.forEach(element => {
            this.frais.push({
                "dateCreation": this.moment(element.dateCreation).format('DD/MM/YYYY'),
                "nature": element.nature.libelle,
                "montant": element.montant+"€"
            })
        });


        let docDefinition = {
            content: [
                { text: 'Note de frais', style: 'title' },
                " ",
                { text: 'Détail de la mission', style: 'header' },
                {
                    table: {
                        widths: ['*', '*'],
                        body: [
                            [
                                { border: [false, false, false, false], text: "Date de début : " + this.moment(this.detailMission.dateDebut).format('DD/MM/YYYY') },
                                { border: [false, false, false, false], text: this.detailMission.prime == null ? 'Estimation prime : 0€' : this.detailMission.prime + "€" }
                            ],
                            [
                                { border: [false, false, false, false], text: "Date de fin : " + this.moment(this.detailMission.dateFin).format('DD/MM/YYYY') },
                                { border: [false, false, false, false], text: "Plafond note de frais : " + this.detailMission.natureMissionInit.plafondFrais + "€" }
                            ],
                            [
                                { border: [false, false, false, false], text: "Nature : " + this.detailMission.natureMissionInit.libelle },
                                { border: [false, false, false, false], text: this.detailMission.natureMissionInit.depassementFrais ? 'Dépassement autorisé : Oui' : 'Dépassement autorisé : Non' }
                            ],
                            [
                                { border: [false, false, false, false], text: "Ville de départ : " + this.detailMission.villeDepart.libelle },
                                { border: [false, false, false, false], text: this.detailMission.deductionPrime <= 0 ? 'Déduction prime : 0€' : this.detailMission.deductionPrime == null ? 'Déduction prime : 0€' : "Déduction prime : " + this.detailMission.deductionPrime + "€" }
                            ],
                            [
                                { border: [false, false, false, false], text: "Ville d'arrivée : " + this.detailMission.villeArrivee.libelle },
                                { border: [false, false, false, false], text: "" }
                            ]
                        ]
                    }
                },
                " ",
                { text: 'Les notes de frais', style: 'header' },
                this.table(this.frais, ['dateCreation', 'nature', 'montant'])
            ],
            styles: {
                title: {
                    fontSize: 24,
                    bold: true,
                    alignment: 'center'
                },
                header: {
                    fontSize: 18,
                    bold: true
                },
                subheader: {
                    fontSize: 15,
                    bold: true
                }

            }
        };
        this.pdfMake.createPdf(docDefinition).open();
    }

    saveIdMission(id, dateDebut, dateFin) {
        sessionStorage.setItem("idMission", id)
        sessionStorage.setItem("dateDebut", dateDebut)
        sessionStorage.setItem("dateFin", dateFin)
    }

}
