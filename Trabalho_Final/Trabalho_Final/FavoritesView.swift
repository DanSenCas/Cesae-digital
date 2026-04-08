//
//  FavoritesView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 27/03/2026.
//

import SwiftUI

struct FavoritesView: View {
    
    @Bindable var favoriteVM = FavoritesViewModel()
    
    
    var body: some View {
        NavigationStack{
            List(favoriteVM.favoritePokemon, id: \.name) { pokemon in
                NavigationLink(destination: PokemonDetailView(
                    favoriteVM: favoriteVM,
                    pokemon: pokemon
                )) {
                    Text(pokemon.name)
                }
                    .swipeActions {
                        Button(role: .destructive) {
                            favoriteVM.removerFavorito(pokemon: pokemon)
                        } label: {
                            Label("Remover", systemImage: "trash")
                        }
                }
            }
        }
    }
}
