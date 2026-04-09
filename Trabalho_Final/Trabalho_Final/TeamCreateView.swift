//
//  TeamCreateView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 08/04/2026.
//

import SwiftUI

struct TeamCreateView: View {
    
    // ViewModel das equipas
    @Bindable var teamVM: TeamViewModel
    // Membros selecionados
    @State private var membros: [String] = []
    // Nome da equipa
    @State private var nomeEquipa = ""
    // ViewModel para lista de Pokémon
    @State private var pokemonVM = PokemonViewModel()
    // Texto da search bar
    @State private var searchText = ""
    
    // Equipa a editar (nil se nova)
    var equipaParaEditar: PokemonTeam? = nil
    
    @Environment(\.dismiss) var dismiss
    
    // Filtra Pokémon conforme searchText
    var filteredPokemon: [Pokemon] {
        if searchText.isEmpty {
            return pokemonVM.AllPokemon
        } else {
            return pokemonVM.AllPokemon.filter{ $0.name.localizedCaseInsensitiveContains(searchText) }
        }
    }
    
    // Preenche campos se for edição
    init(teamVM: TeamViewModel, equipaParaEditar: PokemonTeam? = nil) {
        self.teamVM = teamVM
        self.equipaParaEditar = equipaParaEditar
        if let equipa = equipaParaEditar {
            _nomeEquipa = State(initialValue: equipa.name)
            _membros = State(initialValue: equipa.team)
        }
    }
    
    var body: some View {
        NavigationStack {
            // Campo do nome
            VStack {
                TextField("Nome da equipa", text: $nomeEquipa)
            }
            // Lista de Pokémon disponíveis
            List(filteredPokemon) { pokemon in
                Button {
                    // Toggle membro
                    if membros.contains(pokemon.name) {
                        membros.removeAll { $0 == pokemon.name }
                    } else {
                        membros.append(pokemon.name)
                    }
                } label: {
                    HStack {
                        Text(pokemon.name)
                        Spacer()
                        // Checkmark se selecionado
                        if membros.contains(pokemon.name) {
                            Image(systemName: "checkmark")
                                .foregroundColor(.blue)
                        }
                    }
                }
            }
            .navigationTitle("Nova Equipa")
            // Botão guardar
            .toolbar {
                Button("Guardar") {
                    if let antiga = equipaParaEditar {
                        teamVM.removerEquipa(pokemon: antiga)
                    }
                    let novaEquipa = PokemonTeam(name: nomeEquipa, team: membros)
                    teamVM.adicionarEquipa(pokemon: novaEquipa)
                    dismiss()
                }
            }
            .searchable(text: $searchText, prompt: "Procurar Pokemon")
            // Carrega Pokémon ao abrir
            .task {
                await pokemonVM.buscarPokemonAPI()
            }
        }
    }
}
