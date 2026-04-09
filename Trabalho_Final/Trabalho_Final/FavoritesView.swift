//
//  FavoritesView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 27/03/2026.
//

import SwiftUI
struct FavoritesView: View {
    
    // ViewModel dos favoritos
    @Bindable var favoriteVM = FavoritesViewModel()
    
    var body: some View {
        NavigationStack{
            Group{
                // Lista vazia
                if (favoriteVM.favoritePokemon.isEmpty){
                    Text("Nao Tem Pokemons na Lista")
                        .bold()
                        .frame(maxWidth: .infinity)
                }else{
                    // Lista de favoritos
                    List(favoriteVM.favoritePokemon, id: \.name) { pokemon in
                        // Navega para o detalhe
                        NavigationLink(destination: PokemonDetailView(
                            favoriteVM: favoriteVM,
                            pokemon: pokemon
                        )) {
                            Text(pokemon.name)
                        }
                        // Swipe para remover
                        .swipeActions {
                            Button(role: .destructive) {
                                favoriteVM.removerFavorito(pokemon: pokemon)
                            } label: {
                                Label("Remover", systemImage: "trash")
                            }
                        }
                    }
                }
            } // Group fim
            .navigationTitle("Favoritos")
        } //Nav Stack fim
    }// body fim
}
