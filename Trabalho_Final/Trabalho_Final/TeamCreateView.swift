//
//  TeamCreateView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 08/04/2026.
//

import SwiftUI

struct TeamCreateView: View {
    
    @Bindable var teamVM: TeamViewModel
    @State private var membros: [String] = []
    @State private var nomeEquipa = ""
    @State private var pokemonVM = PokemonViewModel()
    
    var equipaParaEditar: PokemonTeam? = nil
    
    @Environment(\.dismiss) var dismiss
    
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
            VStack {
                TextField("Nome da equipa", text: $nomeEquipa)
            }
            List(pokemonVM.AllPokemon) { pokemon in
                Button {
                    if membros.contains(pokemon.name) {
                            membros.removeAll { $0 == pokemon.name }
                        } else {
                            membros.append(pokemon.name)
                        }
                } label: {
                    HStack {
                        Text(pokemon.name)
                        Spacer()
                        if membros.contains(pokemon.name) {
                            Image(systemName: "checkmark")
                                .foregroundColor(.blue)
                        }
                    }
                }
            }
            .navigationTitle("Nova Equipa")
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
            .task {
                await pokemonVM.buscarPokemonAPI()
            }
        }
    }
}
