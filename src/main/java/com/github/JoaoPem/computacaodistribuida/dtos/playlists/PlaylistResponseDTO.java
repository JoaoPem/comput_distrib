package com.github.JoaoPem.computacaodistribuida.dtos.playlists;

import com.github.JoaoPem.computacaodistribuida.models.Music;

import java.util.List;

public record PlaylistResponseDTO(
        Long id,
        String name,
        String userAccountName,
        List<String> musicNameList
) {
}
