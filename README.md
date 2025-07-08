# Library of Card-based Games

## Things that need doing:
-[x] design game mechanics, what interactions are needed?
-[x] create view to display a card
-[ ] create view to display a hand
-[x] write spawn logic to add a whole deck
-[x] write model for cards
-[x] write logic for click and drag
-[ ] write logic for players having hands
-[ ] write logic for dealing cards to hands
-[ ] write logic to allow different game layouts
-[ ] write logic and gameloop for blackjack game
-[ ] write logic for groups of cards
-[ ] optimise so that cards are only spawned when visible
-[ ] improve game visuals (simple shadows, textures)
-[ ] write logic for simple AI opponents
-[ ] create animations for AI moves
-[ ] main menu (set up using scenes)


## Notes:
### Architecture
- Model() -> defines what the thing is initially 
- Component(Model) -> store the state of the thing 
- View(Component) -> display the state of the thing

 Create bindings between the properties in the component and the properties in the view to ensure the view stays in sync with the game state.