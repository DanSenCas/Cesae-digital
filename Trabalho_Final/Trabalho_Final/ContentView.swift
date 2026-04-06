		//
//  ContentView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 27/03/2026.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        
        TabView {
            PokemonListView()
                .tabItem {
                    Label("Pokédex", systemImage: "list.bullet")
                }
            
            FavoritesView()
                .tabItem {
                    Label("Favoritos", systemImage: "star.fill")
                }
        }
    }
}

#Preview {
    ContentView()
}
