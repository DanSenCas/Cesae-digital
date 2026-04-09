//
//  PokemonListView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 27/03/2026.
//

import SwiftUI
struct PokemonListView: View {
    
    // ViewModel da lista
    @State private var ViewModel = PokemonViewModel()
    // ViewModel dos favoritos
    @Bindable var favoritesVM: FavoritesViewModel
    
    // Texto da search bar
    @State private var searchText = ""
    
    // Filtra lista conforme searchText
    var filteredPokemon: [Pokemon] {
        if searchText.isEmpty {
            return ViewModel.AllPokemon
        } else {
            return ViewModel.AllPokemon.filter{ $0.name.localizedCaseInsensitiveContains(searchText) }
        }
    }
    
    var body: some View {
        NavigationStack{
            List(filteredPokemon) { pokemon in
                // Navega para o detalhe
                NavigationLink(destination: PokemonDetailView(favoriteVM: favoritesVM, pokemon: pokemon)){
                    HStack {
                        // Número do Pokédex
                        Text(String(format: "#%03d", pokemon.pokedexId))
                            .foregroundColor(.gray)
                        Text(pokemon.name.capitalized)
                    }
                }
                // Swipe para favoritar/desfavoritar
                .swipeActions {
                    Button {
                        favoritesVM.toggleFavorito(pokemon: pokemon)
                    } label: {
                        Label(
                            favoritesVM.verificarFavorito(pokemon: pokemon) ? "Remover" : "Adicionar",
                            systemImage: favoritesVM.verificarFavorito(pokemon: pokemon) ? "heart.fill" : "heart"
                        )
                    }
                    .tint(favoritesVM.verificarFavorito(pokemon: pokemon) ? .red : .green)
                }
            }
            .searchable(text: $searchText, prompt: "Procurar Pokemon")
            .navigationTitle("Pokedex")
            // Carrega Pokémon ao abrir
            .task {
                await ViewModel.buscarPokemonAPI()
            }
        }
    }
}
