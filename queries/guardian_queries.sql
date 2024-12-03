-- Q1 view child grades
SELECT 
    C.course_Name, 
    G.grade_Value, 
    G.term, 
    G.comments
FROM 
    Grade G
JOIN 
    Course C ON G.course_Id = C.course_Id
WHERE 
    G.student_Id = ?;


-- Q2 view child daily or weekly schedule
SELECT 
    SC.schedule_Date, 
    SC.start_Time, 
    SC.end_Time, 
    C.course_Name, 
    CL.room_Number
FROM 
    Schedule SC
JOIN 
    Course C ON SC.course_Id = C.course_Id
JOIN 
    Classroom CL ON CL.classroom_Id = SC.classroom_Id
WHERE 
    C.course_Id = ? AND SC.schedule_Date BETWEEN ? AND ?;


-- Q3 new student attendance record
SELECT 
    A.date, 
    A.status,
    A.remarks
FROM 
    Attendance A
WHERE 
    A.student_Id = ?;


-- Q4 view school announcments
SELECT 
    created_on, 
    title, 
    content
FROM 
    Announcements
ORDER BY 
    created_on DESC;
