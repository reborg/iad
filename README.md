# Italian Agile Day conference REST server

Borrowing inspiration from the [Devoxx Schedule Rest Inerface](http://www.devoxx.com/display/Devoxx2K10/Schedule+REST+interface), this is the preliminary version of the Italian Agile Day 2012 conference rest server. 

## Start the server

lein run

## API

* Create an event:

    curl -v -H "Content-Type: application/json" -X POST -d '{"title":"whoa","description":"ops","category":"my","active":"true"}' http://localhost:8080/events

* List all events:

    curl http://localhost:8080/events

* Show a specific event:

    curl http://localhost:8080/events/1

* Retrieve all events for a specific category:

    curl http://localhost:8080/events/@cat1

* Remove an event:

    curl -i -H "Accept: application/json" -X DELETE http://localhost:8080/events/1

* Update an event

    curl -v -H "Content-Type: application/json" -X PUT -d '{"title":"change","description":"change","category":"my","active":"true"}' http://localhost:8080/events/1

## License

Copyright (C) 2012 The @reborg @ramptop collective

Distributed under the Eclipse Public License, the same as Clojure.
