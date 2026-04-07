//
//  PokemonDetailView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 07/04/2026.
//

import SwiftUI

struct PokemonDetailView: View {
    
    @State private var ViewModel = PokemonDetailViewModel()
    
    let pokemon: Pokemon
    
    var body: some View {
        VStack{
            if let detalhe = ViewModel.detalhe {
                AsyncImage(url: URL(string: detalhe.sprites.other.officialArtwork.front_default)) { image in
                    image
                        .resizable()
                        .scaledToFit()
                        .frame(width: 200, height: 200)
                } placeholder: {
                    ProgressView()
                }
                Text(detalhe.name)
                HStack {
                    ForEach(detalhe.types, id: \.type.name) { entrada in
                        Text(entrada.type.name)
                    }
                }
                Text("Altura: \(String(format: "%.1f", Double(detalhe.height) / 10.0)) M")
                Text("Peso: \(String(format: "%.1f", Double(detalhe.weight) / 10.0)) KG")
            } else {
                Text("A carregar...")
            }
        }
        .task {
            await ViewModel.buscarDetalhe(name: pokemon.name)
        }
    }
}
