% 8

% 8.1

%%%
% gramática
%

det(s-m) --> [o].
det(p-m) --> [os].
det(s-f) --> [a].
det(p-f) --> [as].

preposition(_) --> [de].
preposition(s-f) --> [da].
preposition(s-m) --> [do].

%% Adicionado segundo parametro
noun(s-m, writer) --> [Fernando Pessoa].
noun(s-m, book) --> [Os Maias].
noun(s-m, country) --> [Portugal].
noun(s-m, century) --> [XXVI].
noun(s-m, writer) --> [escritor].
noun(p-m, writer) --> [escritores].
noun(s-m, country) --> [país].
noun(p-m, country) --> [paises].
noun(s-m, writer) --> [Eca de Queiroz].

%% Adicionados segundo e terceiro parametros
verbo(s, escrever, S) --> [escreveu], {writer(S)}.
verbo(p, escrever, S) --> [escreveram], {writer(S)}.
verbo(s, nascer, S) --> [nasceu], {writer(S)}.
verbo(p, nascer, S) --> [nasceram], {writer(S)}.
verbo(s, ser, S) --> [e].
verbo(p, ser, S) --> [sao].
verbo(p, viver, S) --> [viveram].
verbo(s, viver, S) --> [viveu].

%%%
% base de dados
%

escritor(escritor).
escritor(Fernando Pessoa).
escritor(Eca de Queiroz).

%humano([]).
%humano([H|T]) :- humano(H), humano(T).

escrever(OsMaias, EcaDeQueirz).
nascer(FernndoPessoa, Portugal)
viver(EcaDeQueiroz, Portugal).


ser(FernandoPessoa, escritor).
ser(X, escritor) :- humano(X).

%%% AULA
frase(A, S, Ob) --> sn(N, S), sv(N, A, Ob, S).

sn(N, S) --> determinante(N-G), nome(N-G, S).
sn(N, S) --> nome(N-_, S).

sv(N, gostar, Ob, S) --> verbo(N, gostar, S), {!}, preposicao(N1-G1), nome(N1-G1, Ob). % chavetas garantem cut, seria interpretado como um predicado
sv(N, A, Ob, S) --> verbo(N, A, S), sn(_, Ob).

% TODO
/* concorda_frase(A, S, Ob) :-
    P =.. */

% 8.2

%%%
% gramática
%

/**
 * qt - quantitativo
 * ql - qualitativo
 */
pron_inter(_-_, ql) --> [quem].
pron_inter(p-_, ql) --> [quais].
pron_inter(p-m, qt) --> [quantos].
pron_inter(p-f, qt) --> [quantas].

pronome(_) --> [que].

/**
 * Q - Quantificador/Tipo de resposta
 * A - Ação
 * At - Atributo de sujeito
 * Ob - Objeto
 */
frase_i(Q, A, At, Ob) --> si(N, At, Q), sv(N, A, Ob, _).

si(N, At, Q) --> pron_inter(N-G, Q), sni(N-G, At).
si(N, _, Q) --> pron_inter(N-G, Q).

sni(N-G, At) --> nome(N-G, At).
sni(N-G, At) --> determinante(N-G), nome(N-G, At), [que].

responde(Q, A, At, Ob) :-
    var(At), % At nao inicializado
    P =.. [A, S, Ob],
    findall(S, P, L),
    (Q = ql, !, write(L) ;
    length(L, N), write(N)).

responde(Q, A, At, Ob) :-
    nonvar(At), % At inicializado
    P =.. [A, S, Ob],
    findall(S, (P, ser(S, At)), L),
    (Q = ql, !, write(L) ;
    length(L, N), write(N)).
