//
//  TeamListView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 08/04/2026.
//

import SwiftUI

struct TeamListView: View {
    
    // ViewModel das equipas
    @Bindable var teamVM: TeamViewModel
    // Controla sheet de criar equipa
    @State private var mostrarCriar = false
    // Equipa selecionada para editar
    @State private var equipaParaEditar: PokemonTeam? = nil
    
    var body: some View {
        NavigationStack {
            // Lista de equipas
            List(teamVM.pokemonTeam) { equipa in
                // Navega para o detalhe
                NavigationLink(destination: TeamDetailView(equipa: equipa)){
                    Text(equipa.name)
                }
                // Swipe direita - apagar
                .swipeActions {
                    Button(role: .destructive) {
                        teamVM.removerEquipa(pokemon: equipa)
                    } label: {
                        Label("Remover", systemImage: "trash")
                    }
                }
                // Swipe esquerda - editar
                .swipeActions(edge: .leading) {
                    Button {
                        equipaParaEditar = equipa
                    } label: {
                        Label("Editar", systemImage: "pencil")
                    }
                    .tint(.blue)
                }
            }
            // Sheet criar nova equipa
            .sheet(isPresented: $mostrarCriar) {
                TeamCreateView(teamVM: teamVM)
            }
            // Sheet editar equipa existente
            .sheet(item: $equipaParaEditar) { equipa in
                TeamCreateView(teamVM: teamVM, equipaParaEditar: equipa)
            }
            .navigationTitle("Equipas")
            // Botão criar equipa
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
