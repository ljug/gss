create view  ArticleTo As select MT.idArticle id , MT.MouvementTo mto , SUM(MT.qt) plusqt
FROM
    Mouvement MT
       GROUP BY MT.MouvementTo, MT.idArticle