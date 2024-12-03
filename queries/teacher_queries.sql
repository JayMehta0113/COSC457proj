-- Q1 retreive class roster
SELECT 
    S.student_Id, 
    S.first_Name, 
    S.grade_Level, 
    G.first_Name AS Guardians_First_Name, 
    G.last_Name AS Guardian_Last_Name, 
    G.phone_Number AS Guardian_Contact, 
    T.first_Name AS Homeroom_Teacher_First_Name, 
    T.last_Name AS Homeroom_Teacher_Last_Name
FROM 
    Teaches TE
JOIN 
    Student S ON S.homeroom_Teacher_Id = TE.teacher_Id
JOIN 
    Guardian G ON G.guardian_Id = S.guardian_Id
JOIN 
    Teacher T ON T.teacher_Id = S.homeroom_Teacher_Id
WHERE 
    TE.teacher_Id = ? AND TE.course_Id = ?;

-- Q2 view student profile for assigned class
SELECT 
    S.student_Id, 
    S.first_Name, 
    S.grade_Level, 
    G.first_Name AS Guardian_First_Name, 
    G.last_Name AS Guardian_Last_Name, 
    G.phone_Number AS Guardian_Contact, 
    A.status AS Attendance_Status, 
    GR.grade_Value, 
    GR.comments
FROM 
    Student S
JOIN 
    Guardian G ON G.guardian_Id = S.guardian_Id
LEFT JOIN 
    Attendance A ON A.student_Id = S.student_Id
LEFT JOIN 
    Grade GR ON S.student_Id = GR.student_Id
WHERE 
    S.student_Id = ? AND GR.course_Id = ?;



-- Q3 record or edit student grade
INSERT INTO Grade (student_Id, course_Id, grade_Value, term, comments)
VALUES (?, ?, ?, ?, ?)
ON DUPLICATE KEY UPDATE 
    grade_Value = ?, 
    comments = ?;


-- Q4 view teacher schedule
SELECT 
    SC.schedule_Date, 
    SC.start_Time, 
    SC.end_Time, 
    C.room_Number
FROM 
    Schedule SC
JOIN 
    Classroom C ON C.classroom_Id = SC.classroom_Id
WHERE 
    SC.course_Id IN (
        SELECT course_Id 
        FROM Teaches 
        WHERE teacher_Id = ?
    );
    
-- Q5 post assignment deadlines
INSERT INTO Assignments (teacher_Id, course_Id, title, description, due_Date, status)
VALUES (?, ?, ?, ?, ?, ?);

