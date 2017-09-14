export default class primeCtrl {
    constructor(loginService, $location, $http, apiUrls, chartjs) {
        this.loginService = loginService
        this.apiUrls = apiUrls
        this.$location = $location
        this.$http = $http
        this.publicPath = publicPath
        this.chartjs = chartjs

        this.missions = new Array()
        this.showedMissions = new Array()
        this.yearsAvailable = new Array()
        this.selectedYear = ""
        this.months = [
            "janv-", "févr-", "mars-", "avr-", "mai-", "juin-", "juil-", "août-", "sept-", "oct-", "nov-", "déc-"
        ]

        this.initData()

        this.order = "dateDebut";
        this.triInverse = false;
    }

    getYearOfDate(date) {
        return date.split("-")[0]
    }

    getMonthOfDate(date) {
        return date.split("-")[1]
    }

    getPrimesForYear(year) {
        return this.missions.filter(mission => this.getYearOfDate(mission.dateDebut) === year || this.getYearOfDate(mission.dateFin) === year)
            .map(mission => mission.prime)
    }

    initData() {
        let userMatricule = this.loginService.getUserMatricule()
        this.findAllMissionsForUser(userMatricule).then(missions => {
            this.missions = missions
            missions.forEach(mission => {
                this.yearsAvailable.push(this.getYearOfDate(mission.dateFin))
            })
            this.yearsAvailable = this.yearsAvailable.filter(function (item, pos, self) {
                return self.indexOf(item) == pos;
            })
        })
    }

    findAllMissionsForUser(matricule) {
        return this.$http.get(`${this.apiUrls.missionsEchues}`)
            .then(missions => {
                return missions.data
            },
            error => {
                // console.log("loginService: checkUser(): error:", error);
            })
            .then(missions => {
                return missions.filter(
                    mission => mission.utilisateurMatricule === matricule
                )
            })
    }

    updateOrderEtTri(order) {
        this.order = order;
        this.triInverse = !this.triInverse;
    }

    createPrimesGraphForYear(year) {
        this.showedMissions = this.missions.filter(mission => this.getYearOfDate(mission.dateFin) === year)
        
        let valuesY = new Array(12).fill(0)        
        this.showedMissions.forEach(mission => {
            valuesY[this.getMonthOfDate(mission.dateFin) - 1] += mission.prime
        })

        let labelsX = this.months.map(month => month + year.slice(-2))

        this.createGraph(year, valuesY, labelsX)
    }

    createGraph(year, values, labelsX) {
        let ctx = "myChart"
        new this.chartjs(ctx, {
            type: 'line',
            data: {
                labels: labelsX,
                datasets: [{
                    label: `Primes année ${year}`,
                    data: values,
                    backgroundColor: [
                        'rgba(54, 162, 235, 0.2)'
                    ],
                    borderColor: [
                        'rgba(54, 162, 235, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });
    }

    exportDataAsExcel() {
        
    }
}

primeCtrl.$inject = ['loginService', '$location', '$http', 'apiUrls', 'chartjs']