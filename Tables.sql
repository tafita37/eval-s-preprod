-- Base
    \c postgres
    drop database construction;
    create database construction;
    \c construction

-- Tables 
    -- BTP
        create table btp(
            id_btp serial primary key,
            email_btp text unique not null,
            mdp_btp text not null
        );

    -- Client
        create table client(
            id_client serial primary key,
            numero_client text unique not null
        );

    -- Unite
        create table unite(
            id_unite serial primary key, 
            nom_unite varchar(30) unique not null
        );
        
    -- Type de travail
        create table type_travail(
            id_type_travail serial primary key, 
            nom_type_travail text unique not null, 
            numero text unique not null
        );

    -- Travail
        create table travail(
            id_travail serial primary key, 
            nom_travail text unique not null, 
            numero text unique not null, 
            id_unite int references unite(id_unite) not null, 
            prix_unitaire double precision
        );

    -- Type de maison
        create table type_maison(
            id_type_maison serial primary key, 
            nom_type_maison text unique not null, 
            duree_jour double precision not null,
            description_type_maison text not null,
            surface double precision
        );

    -- Type de maison et travail
        create table type_maison_travail(
            id_type_maison_travail serial primary key,
            id_type_maison int references type_maison(id_type_maison) not null, 
            id_travail int references travail(id_travail), 
            quantite double precision not null,
            unique(id_type_maison, id_travail)
        );

    -- Type de finition
        create table type_finition(
            id_type_finition serial primary key, 
            nom_type_finition text unique not null, 
            pourcentage_augmentation double precision not null
        );

    -- Lieu
        create table lieu(
            id_lieu serial primary key,
            nom_lieu text unique not null
        );

    -- Devis
        create table devis(
            id_devis serial primary key, 
            ref_devis text unique,
            id_client int references client(id_client) not null, 
            id_type_maison int references type_maison(id_type_maison) not null, 
            id_type_finition int references type_finition(id_type_finition) not null, 
            date_debut date not null, 
            date_devis date,
            id_lieu int references lieu(id_lieu),
            prix_total double precision not null, 
            montant_payer double precision not null default 0,
            duree_type_maison double precision not null,
            pourcentage_augmentation_finition double precision not null,
            en_cours int not null default 0
        );

    -- Devis et travail
        create table historique_devis_travail(
            id_historique_devis_travail serial primary key,
            id_devis int references devis(id_devis) not null,
            id_travail int references travail(id_travail) not null,
            quantite_travail double precision not null,
            prix_unitaire_travail double precision not null
        );

    -- Paiement
        create table paiement(
            id_paiement serial primary key, 
            id_devis int references devis(id_devis) not null, 
            montant double precision not null, 
            date_paiement date not null,
            ref_paiement text unique
        );

    -- Table temporaire maison travaux
        create table tmp_maison_travaux(
            id_tmp_maison_travaux serial primary key,
            type_maison text not null,
            description text not null,
            surface double precision not null,
            code_travaux text not null,
            type_travaux text not null,
            unite text not null,
            prix_unitaire double precision not null,
            quantite double precision not null,
            duree_travaux double precision not null
        );

    -- Table temporaire devis
        create table tmp_devis(
            id_tmp_devis serial primary key,
            client text not null,
            ref_devis text not null,
            type_maison text not null,
            finition text not null,
            taux_finition double precision not null,
            date_devis date not null,
            date_debut date not null,
            lieu text not null
        );

    -- Table temporaire de paiement
        create table tmp_paiement(
            id_tmp_paiement serial primary key,
            ref_devis text not null,
            ref_paiement text not null,
            date_paiement date not null,
            montant double precision not null
        );