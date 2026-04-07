//
//  PokemonDetailViewModel.swift
//  Trabalho_Final
//
//  Created by iMac05 on 07/04/2026.
//

import Foundation

@Observable
class PokemonDetailViewModel {
    
    //Variavel para guardar os detalhes
    var detalhe: PokemonDetail?
    
    //Funçao para ir buscar os detalhes a dita API
    func buscarDetalhe(name:String) async{
        
        guard let BDPokemonDetalhes = URL(string: "https://pokeapi.co/api/v2/pokemon/\(name)") else { return }
        
        do{
            let (dados, resposta) = try await URLSession.shared.data(from: BDPokemonDetalhes)
            detalhe = try JSONDecoder().decode(PokemonDetail.self, from: dados)
            
        }catch{
            print("Erro: \(error)")
        }
    }
}
