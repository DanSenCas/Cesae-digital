//
//  PokemonViewModel.swift
//  Trabalho_Final
//
//  Created by iMac05 on 06/04/2026.
//

import Foundation

@Observable
class PokemonViewModel {
    
    //Array list que guarda o modelo pokemon
    var AllPokemon: [Pokemon] = []
    
    //Funçao async para esperart pela resposta da internet
    func buscarPokemonAPI() async{
        //Objectivo ir buscar os dados e guardar na lista
        
        
        //guard para o caso de o url estiver errado ou com algum erro
        guard let BDPokemon = URL(string: "https://pokeapi.co/api/v2/pokemon?limit=151") else { return }
        
        //Try catch para ver se lida com os erros na api
        //(Resultado)Try catch para guardar o resultado em uma nova variavel
        do{
            let (dados, resposta) = try await URLSession.shared.data(from: BDPokemon)
            let resultado = try JSONDecoder().decode(PokemonListResponse.self, from: dados)
            
            //Para cada resultado, vai criar um pokemon porque falta um id criado por nos (.map serve como um for loop)
            AllPokemon = resultado.results.map { item in
                Pokemon(name: item.name)
            }
            
            
        } catch {
            print("Erro: \(error)")
        }
        
    }
    
}
