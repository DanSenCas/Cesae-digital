//
//  FavoritesViewModel.swift
//  Trabalho_Final
//
//  Created by iMac05 on 07/04/2026.
//
import Foundation
@Observable
class FavoritesViewModel {
    
    // Lista de favoritos
    var favoritePokemon: [Pokemon] = []
    
    // Carrega favoritos guardados
    init() {
        if let data = UserDefaults.standard.data(forKey: "favoritos"),
           let guardados = try? JSONDecoder().decode([Pokemon].self, from: data) {
            favoritePokemon = guardados
        }
    }
    
    // Adiciona e guarda localmente
    func adicionarFavorito(pokemon: Pokemon){
        if !favoritePokemon.contains(where: { $0.name == pokemon.name }) {
            favoritePokemon.append(pokemon)
        }
        if let data = try? JSONEncoder().encode(favoritePokemon) {
            UserDefaults.standard.set(data, forKey: "favoritos")
        }
    }
    
    // Remove e atualiza localmente
    func removerFavorito(pokemon: Pokemon){
        favoritePokemon.removeAll { $0.name == pokemon.name }
        if let data = try? JSONEncoder().encode(favoritePokemon) {
            UserDefaults.standard.set(data, forKey: "favoritos")
        }
    }
    
    // Verifica se é favorito
    func verificarFavorito(pokemon: Pokemon) -> Bool{
        return favoritePokemon.contains { $0.name == pokemon.name }
    }
    
    // Alterna entre adicionar e remover
    func toggleFavorito(pokemon: Pokemon) {
        if verificarFavorito(pokemon: pokemon) {
            removerFavorito(pokemon: pokemon)
        } else {
            adicionarFavorito(pokemon: pokemon)
        }
    }
}
