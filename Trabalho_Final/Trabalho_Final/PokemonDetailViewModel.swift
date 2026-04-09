//
//  PokemonDetailViewModel.swift
//  Trabalho_Final
//
//  Created by iMac05 on 07/04/2026.
//

import Foundation

@Observable
class PokemonDetailViewModel {
    
    // Detalhes do Pokémon selecionado
    var detalhe: PokemonDetail?
    
    // Vai buscar detalhes pelo nome
    func buscarDetalhe(name: String) async {
        
        // Valida o URL com o nome do Pokémon
        guard let BDPokemonDetalhes = URL(string: "https://pokeapi.co/api/v2/pokemon/\(name)") else { return }
        
        do {
            // Chamada à API
            let (dados, _) = try await URLSession.shared.data(from: BDPokemonDetalhes)
            // Decode para PokemonDetail
            detalhe = try JSONDecoder().decode(PokemonDetail.self, from: dados)
            
        } catch {
            print("Erro: \(error)")
        }
    }
}
