sentence -->subject,verbComp.
verbComp --> verbAux, verb,object |verb,object.
subject -->noun | modifier,noun | verb, noun.
object -->noun|modifier,noun| modifier,modifier, noun | modifier, adjective, noun.
adjective --> [famous].

modifier --> [the].
modifier --> [in].
modifier --> [a].

noun -->[fernandoPessoa].
noun --> [osMaias].
noun --> [ecaDeQueiroz].
noun --> [writer].
noun --> [book].
noun --> [portugal].
noun --> [xviiiCentury].

verb -->[wrote].
verb --> [born].
verb -->[was].
verb --> [is].
verb -->[did].
verbAux -->[was].
verbAux --> [is].
verbAux -->[did].

born([ixxcentury],[ecaDeQueiroz]).
wrote([jamesjoyce],[ulysses]).
wrote([ecaDeQueiroz],[osMaias]).
born([portugal],[fernandoPessoa]).
