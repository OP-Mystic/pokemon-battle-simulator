# Pokémon Battle Simulator

A command-line Pokémon battle simulator written in plain Java, with no external libraries or frameworks. Pick a team, battle an AI opponent, manage items mid-fight, and spend money earned from wins to unlock additional Pokémon in a persistent shop.

## Features

- **Turn-based battles** with speed-based move ordering, 4 moves per Pokémon, and PP tracking
- **Type effectiveness system** — a full 18x18 type chart covering all type matchups, including dual-typing
- **Status conditions** — burn, poison, and paralysis, each with real gameplay effects (stat drops, damage-over-time, chance to skip a turn) and dedicated healing items
- **Item system** — Full Restore, Max Revive, Full Heal, Burn Heal, Paralyze Heal, Antidote, and Hyper Potion, usable mid-battle
- **Switching** — swap to another team member mid-battle, with the opponent getting a free hit as the cost of switching
- **Random opponent team selection** — the AI drafts its team from whichever Pokémon you didn't pick
- **Persistent save system** — money and purchased Pokémon are stored in local text files and survive between sessions
- **In-game shop** — spend money earned from battle wins to permanently unlock 9 additional Pokémon, on top of the base roster of 12

## How to run

This project has no dependencies — just a JDK (11 or later works fine, since it uses Java's single-file source launch).

```bash
java gameArea.java
```

This compiles and runs the project in one step. `gameArea` is the entry point; on first run it creates `money.txt` and `bought.txt` automatically in the working directory.

## How to play

From the main menu:
- **1** — Play a battle. Pick 3 of the available Pokémon for your team; the opponent is drafted automatically from the rest.
- **2** — Open the shop to spend earned money on additional Pokémon.
- **0** — Exit.

During a battle, each turn you can attack, use an item, or switch Pokémon. Winning a battle awards a random amount of money, saved automatically to `money.txt`.

## Project structure

| File | Responsibility |
|---|---|
| `gameArea.java` | Entry point — main menu loop, save-file creation |
| `pokeShop.java` | Shop logic — buying additional Pokémon with saved money |
| `SoftRun.java` | Core battle engine — damage calculation, status effects, team selection, the battle loop itself |
| `DataBase.java` | Base roster (12 Pokémon), item definitions, and the type-effectiveness chart |
| `Additionals.java` | The 9 purchasable bonus Pokémon and their shop cost |
| `Pokemon.java` | Pokémon data model (stats, moves, types) |
| `Item.java` | Item data model |

## Notes

The damage formula is loosely modeled on the real Pokémon games' formula (STAB bonus, type effectiveness multiplier, a random variance factor). Save data is stored as plain text and locked read-only between writes to discourage casual editing — it's not designed to resist a determined user, just to keep the files from being accidentally or casually overwritten outside the game's own logic.

This was a self-directed project built to practice object-oriented design, file I/O, and debugging a non-trivial system end-to-end — including tracking down a damage-calculation bug that was causing every hit to deal roughly 10x the intended damage.
