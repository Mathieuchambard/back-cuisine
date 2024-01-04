import pandas as pd
import json 
import requests


class EcologicalBalance:
    def __init__(self,emballage,scoreEF,changementClimatique,appauvrissementCoucheOzone,rayonnementsIonisants,formationPhotochimiqueOzone,particules,acidificationTerrestreEtEauxDouces,eutrophisationTerrestre,
    eutrophisationEauxDouces,eutrophisationMarine,utilisationSol,ecotoxiciteEcosystemesAquatiqueEauDouce,epuisementEau,epuisementEnergie,epuisementMineraux):
        self.emballage = emballage
        self.scoreEF = scoreEF
        self.changementClimatique  = changementClimatique
        self.appauvrissementCoucheOzone = appauvrissementCoucheOzone
        self.rayonnementsIonisants = rayonnementsIonisants
        self.formationPhotochimiqueOzone = formationPhotochimiqueOzone
        self.particules = particules
        self.acidificationTerrestreEtEauxDouces = acidificationTerrestreEtEauxDouces
        self.eutrophisationTerrestre  = eutrophisationTerrestre
        self.eutrophisationEauxDouces = eutrophisationEauxDouces
        self.eutrophisationMarine = eutrophisationMarine
        self.utilisationSol = utilisationSol
        self.ecotoxiciteEcosystemesAquatiqueEauDouce = ecotoxiciteEcosystemesAquatiqueEauDouce
        self.epuisementEau = epuisementEau
        self.epuisementEnergie = epuisementEnergie
        self.epuisementMineraux = epuisementMineraux
	

class HeatBalance:
    def __init__(self,energieKcal,energieKj,eau,proteines,glucides,sucres,lipides,agSature,fibres,sel,sodium,calcium,fer,vitamineD,vitamineC):
        self.energieKcal = int(energieKcal)
        self.energieKj = int(energieKj)
        self.eau = int(eau)
        self.proteines = int(proteines)
        self.glucides = int(glucides)
        self.sucres = int(sucres)
        self.lipides = int(lipides)
        self.agSature = int(agSature)
        self.fibres = int(fibres)
        self.sel = int(sel)
        self.sodium = int(sodium)
        self.calcium = int(calcium)
        self.fer = int(fer)
        self.vitamineD = int(vitamineD)
        self.vitamineC = int(vitamineC)

class Ingredient:
    def __init__(self, name,heatbalance,glutenFree,pregnantPermission,vegan,categorie,ciqual,unitTogramme):
        self.name = name
        self.heatBalance = heatbalance
        self.glutenFree = glutenFree
        self.pregnantPermission = pregnantPermission
        self.vegan = vegan
        self.categorie = categorie
        self.ciqual = ciqual
        if unitTogramme != 'null' :
            self.unitTogramme = float(unitTogramme)
        else :
            self.unitTogramme = -1

    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__, 
            sort_keys=True)


url = 'http://localhost:8080/ingredient'

table = pd.read_csv('versionFinalTable.csv')
table = table.fillna("null")
for i in table.index: 
    heatba = HeatBalance(table["energieKcal"][i],table["energieKj"][i],table["eau"][i],table["proteines"][i],table["glucides"][i],table["sucres"][i],table["lipides"][i],table["agSature"][i],table["fibres"][i],table["sel"][i],table["sodium"][i],table["calcium"][i],table["fer"][i],table["vitamineD"][i],table["vitamineC"][i])
    
    
    ingr = Ingredient(table["name"][i],heatba,bool(table["glutenFree"][i]),bool(table["pregnantPermission"][i]),bool(table["vegan"][i]),table["categorie"][i],int(table["Code CIQUAL"][i]),table["unitTogramme"][i])
    if table["Emballage"][i] != "null" :
        ecologicalBalance = EcologicalBalance(table["Emballage"][i],table["EF(mPt/kg)"][i],table["ChangementClimatique(kgCO2eq/kg)"][i],table["AppauvrissementCoucheOzone(E-06kgCVC11eq/kg)"][i],
    table["RayonnementsIonisants(kBqU-235eq/kg)"][i],table["FormationPhotochimiqueOzone(E-03kgNMVOCeq/kg)"][i],table["Particules (E-06 disease inc./kg)"][i],table["Acidification terrestre et eaux douces (mol H+ eq/kg)"][i]
    ,table["Eutrophisation terreste (mol N eq/kg)"][i],table["Eutrophisation eaux douces (E-03 kg P eq/kg)"][i],table["Eutrophisation marine (E-03 kg N eq/kg)"][i],table["Utilisation du sol (Pt/kg)"][i],
    table["Écotoxicité pour écosystèmes aquatiques d'eau douce (CTUe/kg)"][i],table["Épuisement des ressources eau (m3 depriv./kg)"][i],table["Épuisement des ressources énergétiques (MJ/kg)"][i],table["Épuisement des ressources minéraux (E-06 kg Sb eq/kg)"][i])
        ingr.ecologicalBalance = ecologicalBalance
    requests.post(url,json = json.loads(ingr.toJSON()))
    f = open('jsonIngredient/' + str(table["name"][i]) + '.json', 'w')
    f.write(ingr.toJSON())
    f.close()
