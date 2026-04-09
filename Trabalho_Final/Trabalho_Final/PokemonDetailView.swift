//
//  PokemonDetailView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 07/04/2026.
//

import SwiftUI
struct PokemonDetailView: View {
    
    // ViewModel dos detalhes
    @State private var ViewModel = PokemonDetailViewModel()
    // ViewModel dos favoritos
    @Bindable var favoriteVM: FavoritesViewModel
    
    // Pokemon selecionado na lista
    let pokemon: Pokemon
    
    var body: some View {
        ZStack {
            if let detalhe = ViewModel.detalhe {
                // Gradiente baseado no tipo
                LinearGradient(
                    colors: [corParaTipo(detalhe.types.first?.type.name ?? "normal"), .white],
                    startPoint: .top,
                    endPoint: .bottom
                )
                .ignoresSafeArea()
                
                VStack {
                    // Imagem oficial
                    AsyncImage(url: URL(string: detalhe.sprites.other.officialArtwork.front_default)) { image in
                        image
                            .resizable()
                            .scaledToFit()
                            .frame(width: 200, height: 200)
                    } placeholder: {
                        ProgressView()
                    }
                    
                    // Nome capitalizado
                    Text(detalhe.name.capitalized)
                    
                    // Badges dos tipos
                    HStack {
                        ForEach(detalhe.types, id: \.type.name) { entrada in
                            Text(entrada.type.name.capitalized)
                                .font(.caption)
                                .fontWeight(.bold)
                                .foregroundColor(.white)
                                .padding(.horizontal, 12)
                                .padding(.vertical, 6)
                                .background(corParaTipo(entrada.type.name).opacity(0.8))
                                .cornerRadius(20)
                        }
                    } //HStack fim
                    
                    // Altura e peso convertidos
                    Text("Altura: \(String(format: "%.1f", Double(detalhe.height) / 10.0)) M")
                    Text("Peso: \(String(format: "%.1f", Double(detalhe.weight) / 10.0)) KG")
                    
                } // VStack fim
            } else {
                // Loading
                Text("A carregar...")
            }
        } // ZStack fim
        // Botão favorito
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button {
                    favoriteVM.toggleFavorito(pokemon: pokemon)
                } label: {
                    Image(systemName: favoriteVM.verificarFavorito(pokemon: pokemon) ? "heart.fill" : "heart")
                        .foregroundColor(.white)
                }
            }
        } // Toolbar fim
        // Carrega detalhes ao abrir
        .task {
            await ViewModel.buscarDetalhe(name: pokemon.name)
        }
    }
}
