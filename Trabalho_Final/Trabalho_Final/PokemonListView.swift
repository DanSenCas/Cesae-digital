//
//  PokemonListView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 27/03/2026.
//

import SwiftUI

struct PokemonListView: View {
    
    //Usar umas lista temporaria de pokemons
    let AllPokemon = ["Bulbasaur", "Charmander", "Squirtle", "Pikachu", "Eevee"]
    
    //Texto que o utilizador tem que colocar
    @State private var searchText = ""
    
    //Filtro da lista - Atualiza sempre que a variavel searchText muda
    
    var filteredPokemon: [String]{
        if searchText.isEmpty {
            return AllPokemon
        }else {
            return AllPokemon.filter{ $0.localizedCaseInsensitiveContains(searchText) }
        }
    }
    
    var body: some View {        
        NavigationStack{
            List (filteredPokemon, id: \.self) {
                pokemon in
                Text(pokemon)
            }
            .searchable(text: $searchText, prompt: "Procurar Pokemon")
            .navigationTitle("Pokedex")
            
        }
    }

}
