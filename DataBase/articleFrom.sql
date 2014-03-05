create view ArticleFrom as select MF.idArticle id, MF.MouvementFrom fr , SUM(MF.qt) moinsqt
FROM
    Mouvement MF
       GROUP BY MF.MouvementFrom, MF.idArticle