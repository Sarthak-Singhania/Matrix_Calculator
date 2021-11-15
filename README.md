# Java-Matrix-Calculator
Matrix Calculator with GUI using Java Swing

*Implémentation de l'interface IMatrix à l'aide d'une interface graphique.*

Matrix avec arraylist, plus simple.

À l'ouverture du programe, scanner le fichier texte.  
Itilialiser les Matrices.

**Stocker les Matrices dans un arraylist**  
Les positions *paires* contiennent une string contenant le nom de la Matrix. Les positions *impaires* contiennent la Matrix.

**Affichage du GUI**  
Une fois les Matrices initialisées le GUI s'affiche.  
Le sélecteur de Matrix de la zone 1 affiche tout les noms de Matrices *(Positions paires de l'Arraylist)*  

Lorsque l'utilisateur choisit une Matrix *(action lisener)*, initilisation de la Matrix en zone1. Grid layout avec x nbLines y nbColones.

Chaque element de la Matrix est dans une case qui est pour l'instant non éditable. **action lisener sur les buttons en dessous**. Lorsque l'utilisateur activera le mode édition les buttons deviendront actifs et pourront être utilisés.  

Ajouter une colone ou line causera une réinitialisation de la Matrix zone1 qui sera modifiée à l'aide de add line ou add colone de IMatrix.

En mode *édition* chaque champ *on focus loss* modifie la value à cet endroit dans la Matrix.

**Structure des textfields**  
Afin que l'on puisse utiliser la méthode modifier élément (x,y), le tableau des cellules doit être bi-dimentionnel

	tab[x][y]

Ainsi les x et y corresponderont au x et y de la Matrix avec < nbCol et < nbLines.
