# Bataille

## Interprétation du sujet et règles
Le point C) du sujet précise qu’en cas d’égalité de valeur, les joueurs rejouent une carte dans un duel, ce qui soulève plusieurs questions.

Cela contredit la phrase présente au début du sujet (« La carte gagnante peut l’être par valeur et par couleur. ») ainsi que l’image qui semble sous-entendre que l’ordre de puissance des symboles est le suivant : ♠️ > ♥️ > ♣️ > ♦️.
Je choisis de suivre la spécification du point C) du sujet (« En cas d’égalité de valeur, les joueurs concernés doivent rejouer une carte ») car elle correspond aux règles classiques de la bataille qui stipulent que la couleur des cartes n’a pas d’importance.

Le sujet parle de duel lorsque plus de deux joueurs posent des cartes ayant la même valeur, chacun d’entre eux va devoir rejouer une carte, ce qui techniquement n’est plus un duel. Je précise donc la règle : lorsque deux joueurs ou plus jouent des cartes de même valeur et dont la valeur est la plus élevée parmi toutes les cartes posées, ces joueurs jouent une carte supplémentaire et ainsi de suite jusqu’à ce qu’un joueur pose une carte plus grande que toutes les autres. Les joueurs ayant posé des cartes de valeur inférieure au cours du processus les perdent au profit du joueur qui remporte le tour.

Afin de limiter la complexité de l'implémentation, j’ai choisi de ne pas différencier le tas de cartes gagnées et celui que le joueur a en main. Ainsi, les cartes gagnées sont remises sous le paquet du gagnant à chaque fin de tour. Les cartes sont mélangées avant d’être remises sous le paquet du gagnant du tour, sans quoi on arrive très souvent à des parties infinies (c’est le cas dans une autre implémentation que j’ai testée et qui consiste à replacer les cartes sous le paquet du gagnant dans l’ordre dans lequel elles ont été jouées).

Lorsqu’un joueur n’est pas en capacité de jouer une carte lors d’une bataille car son paquet est vide, il est éliminé.
Ainsi, lorsque plusieurs joueurs engagés dans une bataille arrivent simultanément à la fin de leurs paquets (cas où les joueurs ont joué exactement la même suite de cartes en termes de valeur), ils sont éliminés.
Dans ce cas, l’ensemble des cartes jouées pendant le tour est mis au rebut, y compris celles des éventuels autres joueurs ayant quitté la bataille plus tôt.
S’il ne reste plus de joueur en jeu, la partie se termine par un match nul.

Lors de la distribution des cartes, on s’arrête en faisant en sorte que chacun ait le même nombre de cartes en ne distribuant pas les cartes en trop.

Les règles choisies visent à conserver un comportement testable et reproductible, à l’exception du mélange des cartes.

## Choix techniques
J'ai choisi Java 21 pour ce projet.

Je n'ai pas mis de logger en place. J'aurais pu le faire en redirigeant les sorties vers un fichier afin de ne pas interférer avec les autres affichages sur la sortie standard, en l'occurrence la console.