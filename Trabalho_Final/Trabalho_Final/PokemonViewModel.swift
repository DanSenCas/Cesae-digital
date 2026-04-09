//
//  PokemonViewModel.swift
//  Trabalho_Final
//
//  Created by iMac05 on 06/04/2026.
//

import Foundation

@Observable
class PokemonViewModel {
    
    // Lista de todos os Pokémon
    var AllPokemon: [Pokemon] = []
    
    // Vai buscar 151 Pokémon à API
    func buscarPokemonAPI() async {
        
        // Valida o URL
        guard let BDPokemon = URL(string: "https://pokeapi.co/api/v2/pokemon?limit=151") else { return }
        
        do {
            // Chamada à API
            let (dados, _) = try await URLSession.shared.data(from: BDPokemon)
            // Decode do JSON
            let resultado = try JSONDecoder().decode(PokemonListResponse.self, from: dados)
            
            // Converte resultados em Pokemon - extrai id do URL
            AllPokemon = resultado.results.map { item in
                let id = Int(item.url.split(separator: "/").last ?? "0") ?? 0
                return Pokemon(name: item.name, pokedexId: id)
            }
            
        } catch {
            print("Erro: \(error)")
        }
    }
}
