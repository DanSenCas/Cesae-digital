//
//  TeamListView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 08/04/2026.
//

import SwiftUI

struct TeamListView: View {
    
    @Bindable var teamVM: TeamViewModel
    @State private var mostrarCriar = false
    
    @State private var equipaParaEditar: PokemonTeam? = nil
    
    var body: some View {
        
        NavigationStack {
            List(teamVM.pokemonTeam) { equipa in
                NavigationLink(destination: TeamDetailView(equipa: equipa)){
                    Text(equipa.name)
                }
                .swipeActions{
                    Button(role: .destructive) {
                        teamVM.removerEquipa(pokemon: equipa)
                    } label: {
                        Label("Remover", systemImage: "trash")
                    }
                }
                .swipeActions(edge: .leading) {
                    Button {
                        equipaParaEditar = equipa
                    } label: {
                        Label("Editar", systemImage: "pencil")
                    }
                    .tint(.blue)
                }
            }
            .sheet(isPresented: $mostrarCriar) {
                TeamCreateView(teamVM: teamVM)
            }
            .sheet(item: $equipaParaEditar) { equipa in
                TeamCreateView(teamVM: teamVM, equipaParaEditar: equipa)
            }
            .navigationTitle("Equipas")
            .toolbar {
                Button {
                    mostrarCriar = true
                } label: {
                    Image(systemName: "plus")
                }
            }
        }
    }
}
