		//
//  ContentView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 27/03/2026.
//

import SwiftUI

struct ContentView: View {
    
    // ViewModel partilhado dos favoritos
    @State var favoritesVM = FavoritesViewModel()
    // ViewModel partilhado das equipas
    @State var teamVM = TeamViewModel()
    
    var body: some View {
        // Menu de navegação em baixo
        TabView {
            // Tab da Pokédex
            PokemonListView(favoritesVM: favoritesVM)
                .tabItem {
                    Label("Pokédex", systemImage: "list.bullet")
                }
            // Tab dos favoritos
            FavoritesView(favoriteVM: favoritesVM)
                .tabItem {
                    Label("Favoritos", systemImage: "star.fill")
                }
            // Tab das equipas
            TeamListView(teamVM: teamVM)
                .tabItem {
                    Label("Teams", systemImage: "person.3.fill")
                }
        }
    }
}

#Preview {
    ContentView()
}
