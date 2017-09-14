const apiUrls = {
    utilisateurs: `${API_URL}/utilisateurs`,
    naturesFrais : `${API_URL}/listNatures/frais`,
    frais : `${API_URL}/frais/lister`,
    missions : `${API_URL}/missions/lister`,
    missionsEchues : `${API_URL}/missions/echues/lister`,
    naturesMissions : `${API_URL}/naturesMissions`,
    villes : `${API_URL}/villes/lister`,
    transports : `${API_URL}/transports/lister`,
    statuts : `${API_URL}/statuts/lister`,
    absences : 'https://api-absences.cleverapps.io/absence',
    runTraitementDeNuit : `${API_URL}/tdn/execute`
}

export default apiUrls
