package com.example.rickandmortysearch.utils

import com.example.rickandmortysearch.features.search.dataaccess.model.Location
import com.example.rickandmortysearch.features.search.dataaccess.model.ResponseCharacter
import com.example.rickandmortysearch.features.search.dataaccess.model.transformer.toCharacter
import com.example.rickandmortysearch.features.search.presentationm.toCharacter
import kotlinx.collections.immutable.toPersistentList

fun getCharacterData() = listOf(
    ResponseCharacter(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "Earth (C-137)",
            url = "https://rickandmortyapi.com/api/location/1"
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2",
            "https://rickandmortyapi.com/api/episode/3"
        ),
        url = "https://rickandmortyapi.com/api/character/1",
        created = "2017-11-04T18:48:46.250Z"
    ),
    ResponseCharacter(
        id = 8,
        name = "Adjudicator Rick",
        status = "Dead",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/8.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/28"),
        url = "https://rickandmortyapi.com/api/character/8",
        created = "2017-11-04T20:03:34.737Z"
    ),
    ResponseCharacter(
        id = 15,
        name = "Alien Rick",
        status = "unknown",
        species = "Alien",
        type = "",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/15.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/10"),
        url = "https://rickandmortyapi.com/api/character/15",
        created = "2017-11-04T20:56:13.215Z"
    ),
    ResponseCharacter(
        id = 19,
        name = "Antenna Rick",
        status = "unknown",
        species = "Human",
        type = "Human with antennae",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "unknown",
            url = ""
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/19.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/10"),
        url = "https://rickandmortyapi.com/api/character/19",
        created = "2017-11-04T22:28:13.756Z"
    ),
    ResponseCharacter(
        id = 22,
        name = "Aqua Rick",
        status = "unknown",
        species = "Humanoid",
        type = "Fish-Person",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/22.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/10",
            "https://rickandmortyapi.com/api/episode/22",
            "https://rickandmortyapi.com/api/episode/28"
        ),
        url = "https://rickandmortyapi.com/api/character/22",
        created = "2017-11-04T22:41:07.171Z"
    ),
    ResponseCharacter(
        id = 48,
        name = "Black Rick",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/48.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/22",
            "https://rickandmortyapi.com/api/episode/28"
        ),
        url = "https://rickandmortyapi.com/api/character/48",
        created = "2017-11-05T11:15:26.044Z"
    ),
    ResponseCharacter(
        id = 56,
        name = "Bootleg Portal Chemist Rick",
        status = "Dead",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/56.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/28"),
        url = "https://rickandmortyapi.com/api/character/56",
        created = "2017-11-05T11:34:16.447Z"
    ),
    ResponseCharacter(
        id = 69,
        name = "Commander Rick",
        status = "Dead",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/69.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/22"),
        url = "https://rickandmortyapi.com/api/character/69",
        created = "2017-11-30T11:28:06.461Z"
    ),
    ResponseCharacter(
        id = 72,
        name = "Cool Rick",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "Earth (K-83)",
            url = "https://rickandmortyapi.com/api/location/26"
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/72.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/28"),
        url = "https://rickandmortyapi.com/api/character/72",
        created = "2017-11-30T11:41:11.542Z"
    ),
    ResponseCharacter(
        id = 74,
        name = "Cop Rick",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/74.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/28"),
        url = "https://rickandmortyapi.com/api/character/74",
        created = "2017-11-30T11:48:18.950Z"
    ),
    ResponseCharacter(
        id = 78,
        name = "Cowboy Rick",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/78.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/10",
            "https://rickandmortyapi.com/api/episode/28"
        ),
        url = "https://rickandmortyapi.com/api/character/78",
        created = "2017-11-30T14:15:18.347Z"
    ),
    ResponseCharacter(
        id = 82,
        name = "Cronenberg Rick",
        status = "unknown",
        species = "Cronenberg",
        type = "",
        gender = "Male",
        origin = Location(
            name = "Cronenberg Earth",
            url = "https://rickandmortyapi.com/api/location/12"
        ),
        location = Location(
            name = "Earth (C-137)",
            url = "https://rickandmortyapi.com/api/location/1"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/82.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/6",
            "https://rickandmortyapi.com/api/episode/10"
        ),
        url = "https://rickandmortyapi.com/api/character/82",
        created = "2017-11-30T14:28:54.596Z"
    ),
    ResponseCharacter(
        id = 86,
        name = "Cyclops Rick",
        status = "Dead",
        species = "Humanoid",
        type = "",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/86.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/10",
            "https://rickandmortyapi.com/api/episode/22",
            "https://rickandmortyapi.com/api/episode/28"
        ),
        url = "https://rickandmortyapi.com/api/character/86",
        created = "2017-11-30T20:53:10.382Z"
    ),
    ResponseCharacter(
        id = 103,
        name = "Doofus Rick",
        status = "unknown",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "Earth (J19ζ7)",
            url = "https://rickandmortyapi.com/api/location/31"
        ),
        location = Location(
            name = "Earth (Replacement Dimension)",
            url = "https://rickandmortyapi.com/api/location/20"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/103.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/10",
            "https://rickandmortyapi.com/api/episode/22"
        ),
        url = "https://rickandmortyapi.com/api/character/103",
        created = "2017-12-01T12:29:27.984Z"
    ),
    ResponseCharacter(
        id = 119,
        name = "Evil Rick",
        status = "Dead",
        species = "Humanoid",
        type = "Robot",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/119.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/10"),
        url = "https://rickandmortyapi.com/api/character/119",
        created = "2017-12-26T16:17:16.472Z"
    ),
    ResponseCharacter(
        id = 135,
        name = "Garment District Rick",
        status = "Dead",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/135.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/28"),
        url = "https://rickandmortyapi.com/api/character/135",
        created = "2017-12-26T20:51:43.614Z"
    ),
    ResponseCharacter(
        id = 164,
        name = "Insurance Rick",
        status = "unknown",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/164.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/10"),
        url = "https://rickandmortyapi.com/api/character/164",
        created = "2017-12-29T17:03:08.645Z"
    ),
    ResponseCharacter(
        id = 165,
        name = "Investigator Rick",
        status = "Dead",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/165.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/28"),
        url = "https://rickandmortyapi.com/api/character/165",
        created = "2017-12-29T17:05:15.514Z"
    ),
    ResponseCharacter(
        id = 187,
        name = "Juggling Rick",
        status = "unknown",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/187.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/28"),
        url = "https://rickandmortyapi.com/api/character/187",
        created = "2017-12-29T18:59:47.440Z"
    ),
    ResponseCharacter(
        id = 215,
        name = "Maximums Rickimus",
        status = "Dead",
        species = "Human",
        type = "",
        gender = "Male",
        origin = Location(
            name = "unknown",
            url = ""
        ),
        location = Location(
            name = "Rick's Memories",
            url = "https://rickandmortyapi.com/api/location/126"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/215.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/10",
            "https://rickandmortyapi.com/api/episode/22",
            "https://rickandmortyapi.com/api/episode/51"
        ),
        url = "https://rickandmortyapi.com/api/character/215",
        created = "2017-12-30T14:27:55.489Z"
    )
)

fun getPreviewData() = getCharacterData()
    .map { it.toCharacter() }
    .map { it.toCharacter() }
    .toPersistentList()