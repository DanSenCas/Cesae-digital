//
//  TeamViewModel.swift
//  Trabalho_Final
//
//  Created by iMac05 on 08/04/2026.
//

import Foundation

@Observable
class TeamViewModel {
    
    var pokemonTeam: [PokemonTeam] = []
    
    init() {
        if let data = UserDefaults.standard.data(forKey: "equipas"),
           let guardadas = try? JSONDecoder().decode([PokemonTeam].self, from: data) {
            pokemonTeam = guardadas
        }
    
    }
    
    //Adicionar membro de equipa
    func adicionarEquipa(pokemon: PokemonTeam){
        if !pokemonTeam.contains(where: { $0.name == pokemon.name }) {
            pokemonTeam.append(pokemon)
        }
        if let data = try? JSONEncoder().encode(pokemonTeam) {
            UserDefaults.standard.set(data, forKey: "equipas")
        }
    }
    
    //Remover membro de equipa
    func removerEquipa(pokemon: PokemonTeam){
        pokemonTeam.removeAll { $0.name == pokemon.name }
        if let data = try? JSONEncoder().encode(pokemonTeam) {
            UserDefaults.standard.set(data, forKey: "equipas")
        }
    }
    
}
