		//
//  ContentView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 27/03/2026.
//

import SwiftUI

struct ContentView: View {
    
    @State var favoritesVM = FavoritesViewModel()
    @State var teamVM = TeamViewModel()
    
    var body: some View {
        
        TabView {
            PokemonListView(favoritesVM: favoritesVM)
                .tabItem {
                    Label("Pokédex", systemImage: "list.bullet")
                }
            
            FavoritesView(favoriteVM: favoritesVM)
                .tabItem {
                    Label("Favoritos", systemImage: "star.fill")
                }
            TeamListView(teamVM:teamVM)
                .tabItem {
                    Label("Teams", systemImage: "person.3.fill")
                }
        }
    }
}

#Preview {
    ContentView()
}
