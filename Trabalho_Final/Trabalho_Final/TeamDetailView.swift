//
//  TeamDetailView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 08/04/2026.
//

import SwiftUI

struct TeamDetailView: View {
    
    // Equipa selecionada
    let equipa: PokemonTeam
    
    var body: some View {
        // Lista de membros da equipa
        List(equipa.team, id: \.self) { membro in
            Text(membro)
        }
        // Nome da equipa como título
        .navigationTitle(equipa.name)
    }
}
