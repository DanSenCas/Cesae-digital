//
//  coresTipos.swift
//  Trabalho_Final
//
//  Created by iMac05 on 09/04/2026.
//

import SwiftUI

// Devolve a cor associada a cada tipo
func corParaTipo(_ tipo: String) -> Color {
    switch tipo {
    case "fire": return Color.orange
    case "water": return Color.blue
    case "grass": return Color.green
    case "poison": return Color.purple
    case "electric": return Color.yellow
    case "psychic": return Color.pink
    case "ice": return Color.cyan
    case "dragon": return Color.indigo
    case "dark": return Color.black
    case "fairy": return Color.pink
    case "normal": return Color.gray
    case "fighting": return Color.red
    case "flying": return Color(red: 0.6, green: 0.8, blue: 1.0)
    case "bug": return Color(red: 0.6, green: 0.8, blue: 0.2)
    case "rock": return Color(red: 0.7, green: 0.6, blue: 0.4)
    case "ground": return Color(red: 0.8, green: 0.7, blue: 0.4)
    case "ghost": return Color.indigo.opacity(0.7)
    case "steel": return Color.gray.opacity(0.7)
    default: return Color.gray // tipo desconhecido
    }
}
