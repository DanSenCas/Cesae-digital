//
//  Pokemon.swift
//  Trabalho_Final
//
//  Created by iMac05 on 27/03/2026.
//

//Ficheiro modelo
import Foundation

//Pokemon base - usado na lista
struct Pokemon: Identifiable, Codable {
    let id = UUID()
    let name: String
    let pokedexId: Int
    
    enum CodingKeys: CodingKey {
        case name, pokedexId
    }
}

//Detalhes completos - usado no perfil
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
    
    enum CodingKeys: CodingKey {
        case name, team
    }
}

//Estrutura para fazer decode ao JSON da API
//Sprite da imagem - acesso ao objeto other
struct PokemonSprites: Decodable{
    let other: PokemonOther
}

//Preciso porque na BD esta dentro de muitas "pastas"
struct PokemonOther: Decodable{
    let officialArtwork: PokemonOfficialArtwork
    
    //Como swift nao le o - entao tem que se dizer o que pesquisar usando a codingkey
    enum CodingKeys: String, CodingKey {
        case officialArtwork = "official-artwork"
    }
}

//Imagem oficial do Pokemon
struct PokemonOfficialArtwork: Decodable{
    let front_default: String
}

//Recebe o nome do tipo
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
    let url: String // usado para extrair o pokedexId
}
