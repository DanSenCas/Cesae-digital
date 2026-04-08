//
//  Pokemon.swift
//  Trabalho_Final
//
//  Created by iMac05 on 27/03/2026.
//

//Ficheiro modelo

import Foundation

//A lista so precisa do nome
struct Pokemon: Identifiable {
    
    let id = UUID()
    let name: String
    
}

//Os detalhes é que precisam de um modelo que mostra mais detalhes
struct PokemonDetail: Decodable{
    let id: Int
    let name: String
    let sprites: PokemonSprites
    let height: Int
    let weight: Int
    let types: [PokemonTypeResponse]
}

//Equipa Pokemon
struct PokemonTeam: Identifiable, Codable{
    
    let id = UUID()
    let name: String
    let team: [String]
}


//Estrutura para fazer decode ao JSON da API

//Struct para o sprite da immagem
struct PokemonSprites: Decodable{
    let other: PokemonOther
}

//Preciso porque na BD esta dentro de muitas "pastas"
struct PokemonOther: Decodable{
    let officialArtwork: PokemonOfficialArtwork
    
    //Como swift nao le o - entao tem que se dizer o que pesquisar unsando a codingkey
    enum CodingKeys: String, CodingKey {
        case officialArtwork = "official-artwork"
    }
}
 
struct PokemonOfficialArtwork: Decodable{
    let front_default: String
}

//Recebe o nome do typo
struct PokemonType: Decodable {
    let name: String
}

//Recebe o nome e altera o tipo para colocar no array
struct PokemonTypeResponse: Decodable {	
    let type: PokemonType
}


//Resposta inteira da API
struct PokemonListResponse: Decodable {
    let results: [PokemonResult]
}


//Representa um Pokemon individual
struct PokemonResult: Decodable {
    let name: String
}
