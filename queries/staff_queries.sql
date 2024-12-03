-- Q1 retreive school contact information for teachers, guardians, and admin
SELECT 
    T.first_Name, 
    T.last_Name, 
    T.phone_Number, 
    T.email, 
    T.department AS role
FROM 
    Teacher T

UNION

SELECT 
    G.first_Name, 
    G.last_Name, 
    G.phone_Number, 
    G.email, 
    'Guardian' AS role
FROM 
    Guardian G

UNION

SELECT 
    A.first_Name, 
    A.last_Name, 
    A.phone_Number, 
    A.email, 
    A.position AS role
FROM 
    Admin A;
