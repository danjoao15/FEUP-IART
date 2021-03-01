pl:-
  EI,
  EF,
  pl([[EI]],EF,RES), write(RES).
pl([Cam1|_],EF,Cam1):-
  Cam1=[EF|_].
pl([Cam1|OCams],EF,RES):-
  Cam1=[EF|OCams],
  findall([Eseg|Cam1],(Sucessor(Ea,Eseg),\+member(Eseg|OEs)),Lseq),
  append(Ocams,Lseq,NovosCams),
  pl(NovosCams,Ef,RES).

  % 2.1

  %estado inicial
  estado_inicial(b(0,0)).

  %estado final
  estado_final(b(2,0)).

  %transições entre estados
  sucessor(b(X,Y), b(4,Y)) :- X<4.
  sucessor(b(X,Y), b(X,3)) :- Y<3.
  sucessor(b(X,Y), b(0,Y)) :- X>0.
  sucessor(b(X,Y), b(X,0)) :- Y>0.
  sucessor(b(X,Y), b(4,Y1)) :-
  			X+Y>=4,
  			X<4,
  			Y1 is Y-(4-X).
  sucessor(b(X,Y), b(X1,3)) :-
  			X+Y>=3,
  			Y<3,
  			X1 is X-(3-Y).
  sucessor(b(X,Y), b(X1,0)) :-
  			X+Y<4,
  			Y>0,
  			X1 is X+Y.
  sucessor(b(X,Y), b(0,Y1)) :-
  			X+Y<3,
  			X>0,
  			Y1 is X+Y.
