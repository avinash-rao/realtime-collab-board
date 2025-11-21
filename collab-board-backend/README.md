High-Level Understanding of model relationships
=======================================
In a Trello-style data model:

- A **Board** contains multiple **Lists**
- A **List** contains multiple **Cards**

A **Card** itself contains:
- labels
- checklist
- comments
- assignments
- due date
- (potentially attachments, activity history, etc.)

### Why Cards are *not* embedded inside Boards

We intentionally avoid embedding Cards inside Boards because cards:
* grow large
* get updated very often
* have comments, labels, attachments


Storing cards in their own collection makes these operations fast and efficient.

---

# Data flow when using the app

Case 1: User opens a Board  
What happens?

Backend returns:
```
Board {
    id,
    title,
    members,
    lists: [
        { list1 },
        { list2 }
    ]
}
```

Fine — but what about the cards?

The UI then calls:  
GET /cards?boardId=BOARD123

This fetches all cards separately.

---

Case 2: Adding a Card

User adds a card in list L1:

Card stored in cards collection

Board’s lists[] stays unchanged (list holds only metadata)

---

Case 3: Moving a Card

User drags card from list A → list B:

We update only card.listId

No need to touch Board.lists[]

This is why cards are stored separately — updating is cheap.

---

# CRUD Overview
### ✔ 1. Board CRUD

- Create board
- Get board by ID
- Get boards for a user
- Update board
- Delete board
- Add list to board
- Update list
- Delete list

### ✔ 2. Card CRUD

- Create card
- Get cards by board
- Move card between lists
- Update card
- Delete card

### ✔ 3. User CRUD (minimal for now)

- Create user
- Get by ID  