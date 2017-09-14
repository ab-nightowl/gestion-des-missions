export default class gestionFraisCtrl {
    constructor(gestionFraisService, pdfMake, detailGestionFraisService) {
        this.gestionFraisService = gestionFraisService
        this.sumFraisMission()
        this.findMission()
        this.mission = []
        this.dateNow = new Date();
        this.order = "dateDebut";
        this.triInverse = false;
        this.pdfMake = pdfMake
        this.detailGestionFraisService = detailGestionFraisService
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
                this.findFraisMission(idMission)
            })
    }

    findFraisMission(idMission) {
        this.detailGestionFraisService.findFraisMission(idMission)
            .then(fm => {
                this.fraisMission = fm
                this.creerPdf()
            })

    }

    pdf(idMission) {
        this.findDetailsMission(idMission)
    }

    creerPdf() {
        let docDefinition = {
            content: [
                { text: 'Détail de la mission', style: 'header' },
                {
                    table: {
                        widths: ['*', '*'],
                        body: [
                            ["Date de début : " + this.detailMission.dateDebut, "Estimation prime : ??€"],
                            ["Date de fin : " + this.detailMission.dateFin, "Plafond note de frais : " + this.detailMission.natureMissionInit.plafondFrais + "€"],
                            ["Nature : " + this.detailMission.natureMissionInit.libelle, "Dépassement autorisé : " + this.detailMission.natureMissionInit.depassementFrais],
                            ["Ville de départ : " + this.detailMission.villeDepart.libelle, "Déduction prime : ??€"],
                            ["Ville d'arrivée : " + this.detailMission.villeArrivee.libelle, ""]
                        ]
                    }
                },
                " ",
                { text: 'Les notes de frais', style: 'header' },
                this.table(this.fraisMission, ['dateCreation', 'nature', 'montant'])
            ],
            styles: {
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
