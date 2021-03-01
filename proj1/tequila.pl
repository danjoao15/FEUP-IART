verb([wrote]).
verb([built]).
verb([caused]).

noun([Mary]).
noun([Jane]).
noun([Red]).
noun([Blue]).

event([NineEleven]).
event([NoahArch]).
event([Ulysses]).

wrote([jamesjoyce],[ulysses]).

built([noah],[noaharch]).

caused([bush],[nineeleven]).

adjective([long]).
adjective([short]).
adjective([old]).

determiner([the]).
determiner([a]).

pronoun([which]).
pronoun([whose]).
pronoun([who]).
pronoun([what]).

which([which]).
whose([whose]).
who([who]).
what([what]).

question(Q):-
  append(P,S,Q),
  pronoun(P),
  append(V,E,S),
  verb(V),
  event(E),
  answer(X,E),
  write(X).

answer(X,E):-
  wrote(X,E).
answer(X,E):-
  caused(X,E).
answer(X,E):-
  built(X,E).

sentence(S):-
  append(P, L, S),
  pronoun(P),
  append(NP,VP,L),
  nounphrase(NP),
  verbphrase(VP),
  write(P).
sentence(S):-
  append(NP,VP,S),
  nounphrase(NP),
  verbphrase(VP).

nounphrase(NP):-
  append(D, NE, NP),
  determiner(D),
  nounexpression(NE).
nounphrase(NP):-
  nounexpression(NP).

nounexpression(NE):-
  noun(NE).

nounexpression(NE):-
  append(A, N, NE),
  adjective(A),
  nounexpression(N).

verbphrase(VP):-
  append(V, NP, VP),
  verb(V),
  nounphrase(NP).
