-- Travail et type de maison
    create or replace view v_travail_type_maison
        as
            select
                t.*,
                tmt.id_type_maison_travail,
                tmt.id_type_maison,
                tmt.quantite
            from
                travail t
                    join
                        type_maison_travail tmt
                            on
                                tmt.id_travail=t.id_travail;

-- Stat de montant de devis par mois par annee
    create or replace view v_montant_p_mois_annee
        as
            select 
                ROW_NUMBER() OVER() AS id, 
                coalesce(sum(prix_total*(1+(pourcentage_augmentation_finition/100))), 0) as montant_devis, 
                m.num_mois::integer as mois,
                a.annee::integer as annee
            from 
                (
                    select 
                        generate_series(1, 12) as num_mois
                ) m
            cross join
                (
                    select 
                        distinct extract(year from date_debut) as annee 
                    from
                        devis
                ) a
            left join   
                devis d
            on
                extract(month from d.date_debut) = m.num_mois
                    and
                extract(year from d.date_debut) = a.annee
            group by
                m.num_mois, 
                a.annee
            order by
                m.num_mois, 
                a.annee;

-- Table de travaux temporaire et unite
    create or replace view v_tmp_maison_travaux_unite
        as
            select
                tmt.*,
                u.id_unite
            from 
                tmp_maison_travaux tmt 
                    join 
                        unite u 
                            on 
                                u.nom_unite=tmt.unite;  

-- Tables de travaux temporaire avec maison et travail
    create or replace view v_tmp_maison_travaux
        as
            select
                tmt.*,
                t.id_travail,
                t.numero,
                t.id_unite,
                tm.id_type_maison
            from 
                tmp_maison_travaux tmt 
                    join 
                        type_maison tm 
                            on 
                                tmt.type_maison=tm.nom_type_maison 
                    join 
                        travail t 
                            on 
                                t.nom_travail=tmt.type_travaux;

-- Table de devis temporaire et type de maison et finition
    create or replace view v_tmp_devis_type_maison_finition_lieu_client
        as
            select
                td.*,
                tm.id_type_maison,
                tm.duree_jour, 
                tm.description_type_maison,
                tm.surface,
                tf.id_type_finition,
                pourcentage_augmentation,
                l.id_lieu,
                c.id_client
            from 
                tmp_devis td 
                    join 
                        type_maison tm 
                            on 
                                td.type_maison=tm.nom_type_maison
                    join
                        type_finition tf
                            on
                                tf.nom_type_finition=td.finition
                    join
                        lieu l
                            on
                                l.nom_lieu=td.lieu
                    join
                        client c
                            on
                                c.numero_client=td.client;
          
-- Devis et travai/maison
    create or replace view v_devis_travail_maison
        as
            select
                d.*,
                vttm.id_type_maison_travail,
                vttm.id_travail,
                vttm.quantite,
                vttm.prix_unitaire
            from
                v_travail_type_maison vttm
                    join
                        devis d
                            on
                                d.id_type_maison=vttm.id_type_maison;

-- Devis et table temporaire de paiement
    create or replace view v_devis_tmp_paiement
        as
            select
                d.*,
                tp.ref_paiement, 
                tp.date_paiement, 
                tp.montant
            from 
                devis d
                    join
                        tmp_paiement tp
                            on
                                tp.ref_devis=d.ref_devis;