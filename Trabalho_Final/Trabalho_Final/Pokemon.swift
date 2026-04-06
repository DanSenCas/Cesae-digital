//
//  Pokemon.swift
//  Trabalho_Final
//
//  Created by iMac05 on 27/03/2026.
//

//Ficheiro modelo

import Foundation

struct Pokemon: Identifiable {
    
    let id = UUID()
    let name: String
    
}

//Estrutura para fazer decode ao JSON da API

struct PokemonListResponse: Decodable {
    let results: [PokemonResult]
}
	
struct PokemonResult: Decodable {
    let name: String
}
