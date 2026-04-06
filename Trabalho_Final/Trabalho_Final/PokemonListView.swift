//
//  PokemonListView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 27/03/2026.
//

import SwiftUI

struct PokemonListView: View {
    
    //Usar umas lista temporaria de pokemons
    //let AllPokemon = ["Bulbasaur", "Charmander", "Squirtle", "Pikachu", "Eevee"]
    
    @State private var ViewModel = PokemonViewModel()
    
    //Texto que o utilizador tem que colocar
    @State private var searchText = ""
    
    //Filtro da lista - Atualiza sempre que a variavel searchText muda
    
    var filteredPokemon: [Pokemon] {
        if searchText.isEmpty {
            return ViewModel.AllPokemon
        }else {
            return ViewModel.AllPokemon.filter{ $0.name.localizedCaseInsensitiveContains(searchText) }
        }
    }
    
    var body: some View {        
        NavigationStack{
            List (filteredPokemon) {
                pokemon in
                Text(pokemon.name)
            }
            .searchable(text: $searchText, prompt: "Procurar Pokemon")
            .navigationTitle("Pokedex")
            .task { // task para fazer correr um codigo quando a view aparecer
                await ViewModel.buscarPokemonAPI()
            }
        }
    }
}
