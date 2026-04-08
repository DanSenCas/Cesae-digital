//
//  FavoritesViewModel.swift
//  Trabalho_Final
//
//  Created by iMac05 on 07/04/2026.
//

import Foundation

@Observable
class FavoritesViewModel {
    
    var favoritePokemon: [Pokemon] = []
    
    init() {
        if let guardados = UserDefaults.standard.stringArray(forKey: "favoritos") {
            favoritePokemon = guardados.map { Pokemon(name: $0) }
        }
    }
    
    func adicionarFavorito(pokemon: Pokemon){
        if !favoritePokemon.contains(where: { $0.name == pokemon.name }) {
            favoritePokemon.append(pokemon)
        }
        UserDefaults.standard.set(favoritePokemon.map { $0.name }, forKey: "favoritos")
    }
    
    func removerFavorito(pokemon: Pokemon){
        favoritePokemon.removeAll { $0.name == pokemon.name }
        UserDefaults.standard.set(favoritePokemon.map { $0.name }, forKey: "favoritos")
    }
    
    func verificarFavorito(pokemon: Pokemon) -> Bool{
        return favoritePokemon.contains { $0.name == pokemon.name }
    }
    
    func toggleFavorito(pokemon: Pokemon) {
        if verificarFavorito(pokemon: pokemon) {
            removerFavorito(pokemon: pokemon)
        } else {
            adicionarFavorito(pokemon: pokemon)
        }
    }
}

