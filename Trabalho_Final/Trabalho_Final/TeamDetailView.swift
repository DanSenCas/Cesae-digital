//
//  TeamDetailView.swift
//  Trabalho_Final
//
//  Created by iMac05 on 08/04/2026.
//

import SwiftUI

struct TeamDetailView: View {
    
    let equipa: PokemonTeam
    
    
    var body: some View {
        
        List(equipa.team, id: \.self) { membro in
            Text(membro)
        }
        .navigationTitle(equipa.name)
    }
}
