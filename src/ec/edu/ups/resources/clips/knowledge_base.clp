
; Listar todas las clases: (list-defclasses)
; Listar las clases con las herencias: (browse-classes <class>)

(defclass person
	(is-a USER)
	(slot per_dni
		(type STRING)
		(default "NaN")
	)
	(multislot per_name
		(type STRING)
		(cardinality  1 2)
		(default "no name")
	)
	(multislot per_lastname
		(type STRING)
		(cardinality  1 2)
		(default "no lastname")
	)
	(slot per_age
		(type NUMBER)
		(default 18)
	)
	(multislot per_result
		(type INTEGER)
		(cardinality 1 4)
		(default 0 0 0 0)
	)
	(multislot per_cat_result
		(type SYMBOL)
		(cardinality 1 4)
		(allowed l m h n)
		(default n n n n)
	)
	(multislot per_result_question
		(type INTEGER)
		(cardinality 1 22)
		(default 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0)
	)
)

(defclass teacher
	(is-a person)
	(slot tea_teach_time
		(type FLOAT)
		(default 0.0)
	)
	(slot tea_investigation_time
		(type FLOAT)
		(default 0.0)
	)
)

(defclass student
	(is-a person)
	(slot stu_study_time_day
		(type FLOAT)
		(default 5.0)
	)
	(slot stu_quantity_subjects
		(type INTEGER)
		(default 0)
	)
	(slot stu_student_type
		(type SYMBOL)
		(default r)
		(allowed-symbols r i)
	)
)



;--- Function: emotional-exhaustion

(deffunction sum-emotional-exhaustion
	($?v)
	(bind ?r 0)
	(bind ?r (+ ?r (nth$ 1 $?v)))
	(bind ?r (+ ?r (nth$ 2 $?v)))
	(bind ?r (+ ?r (nth$ 3 $?v)))
	(bind ?r (+ ?r (nth$ 6 $?v)))
	(bind ?r (+ ?r (nth$ 8 $?v)))
	(bind ?r (+ ?r (nth$ 13 $?v)))
	(bind ?r (+ ?r (nth$ 14 $?v)))
	(bind ?r (+ ?r (nth$ 16 $?v)))
	(bind ?r (+ ?r (nth$ 20 $?v)))
)

;--- Function: depersonalization 

(deffunction sum-depersonalization 
	($?v)
	(bind ?r 0)
	(bind ?r (+ ?r (nth$ 5 $?v)))
	(bind ?r (+ ?r (nth$ 10 $?v)))
	(bind ?r (+ ?r (nth$ 11 $?v)))
	(bind ?r (+ ?r (nth$ 15 $?v)))
	(bind ?r (+ ?r (nth$ 22 $?v)))
)

;--- Function: personal accomplishment

(deffunction sum-personal-accomplishment
	($?v)
	(bind ?r 0)
	(bind ?r (+ ?r (nth$ 4 $?v)))
	(bind ?r (+ ?r (nth$ 7 $?v)))
	(bind ?r (+ ?r (nth$ 9 $?v)))
	(bind ?r (+ ?r (nth$ 12 $?v)))
	(bind ?r (+ ?r (nth$ 17 $?v)))
	(bind ?r (+ ?r (nth$ 18 $?v)))
	(bind ?r (+ ?r (nth$ 19 $?v)))
	(bind ?r (+ ?r (nth$ 21 $?v)))
)


;--- Rules for: emotional exhaustion

(defrule stu-cal-emotional-exhaustion
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_result_question $?rq))
	(test
		(eq (nth$ 1 $?r) 0)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	
	;Add new result
	(bind ?ee (sum-emotional-exhaustion $?rq))
	(nth$ 1 (send ?student put-per_result ?ee))

	;Print by console for test
	(bind $?res (send ?student get-per_result))
	(printout t "Resultado:" $?res " Resultado por pregunta:" $?rq crlf)
)

(defrule tea-cal-emotional-exhaustion
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_result_question $?rq))
	(test
		(eq (nth$ 1 $?r) 0)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	
	;Add new result
	(bind ?ee (sum-emotional-exhaustion $?rq))
	(nth$ 1 (send ?teacher put-per_result ?ee))
	
	;Print by console for test
	(bind $?res (send ?teacher get-per_result))
	(printout t "Resultado:" $?res " Resultado por pregunta:" $?rq crlf)
)


;--- Rules for: depersonalization

(defrule stu-cal-depersonalization
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_result_question $?rq))
	(test
		(eq (nth$ 2 $?r) 0)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	
	;Add new result
	(bind ?ee (sum-depersonalization $?rq))
	(nth$ 2 (send ?student put-per_result ?ee))

	;Print by console for test
	(bind $?res (send ?student get-per_result))
	(printout t "Resultado:" $?res " Resultado por pregunta:" $?rq crlf)
)

(defrule tea-cal-depersonalization
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_result_question $?rq))
	(test
		(eq (nth$ 2 $?r) 0)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	
	;Add new result
	(bind ?ee (sum-depersonalization $?rq))
	(nth$ 2 (send ?teacher put-per_result ?ee))
	
	;Print by console for test
	(bind $?res (send ?teacher get-per_result))
	(printout t "Resultado:" $?res " Resultado por pregunta:" $?rq crlf)
)


;--- Rules for: personal accomplishment

(defrule stu-cal-personal-accomplishment
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_result_question $?rq))
	(test
		(eq (nth$ 3 $?r) 0)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	
	;Add new result
	(bind ?ee (sum-personal-accomplishment $?rq))
	(nth$ 3 (send ?student put-per_result ?ee))

	;Print by console for test
	(bind $?res (send ?student get-per_result))
	(printout t "Resultado:" $?res " Resultado por pregunta:" $?rq crlf)
)

(defrule tea-cal-personal-accomplishment
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_result_question $?rq))
	(test
		(eq (nth$ 3 $?r) 0)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	
	;Add new result
	(bind ?ee (sum-personal-accomplishment $?rq))
	(nth$ 3 (send ?teacher put-per_result ?ee))
	
	;Print by console for test
	(bind $?res (send ?teacher get-per_result))
	(printout t "Resultado:" $?res " Resultado por pregunta:" $?rq crlf)
)

(defrule stu-cat-low-burnout
	?student <- (object	(is-a student)
						(per_result_question $?r)
						(per_cat_result $?cr))
	(test
		(eq (nth$ 1 $?cr) n)
	)
=>
	;Add
)

(defrule stu-cat-half-burnout

)

(defrule stu-cat-high-burnout

)

(defrule tea-cat-low-burnout

)

(defrule tea-cat-half-burnout

)

(defrule tea-cat-high-burnout

)


;----------------------------------------------------------
; find-instance
; find-all-instances
; do-for-all-instances

(do-for-all-instances ((?p person))
	(>= ?p:age 18)
	(printout t "nombre:" ?p:person-name " | age:" ?p:age crlf)
)

(deffunction mean_age
	(?cl)
	(bind ?n 0)
	(bind ?sum 0)
	(do-for-all-instances ((?c ?cl)) TRUE
		(bind ?sum (+ ?sum ?c:age))
		(bind ?n (+ ?n 1))
	)
	(/ ?sum ?n)
)



;----------------------------------------------------------

; Crear Instancias.
(make-instance 
	czhizhpon_student of student 
	(per_name "Cesar" "Eduardo")
	(per_lastname "Zhizhpon" "Tacuri")
	(per_age 21)
	(stu_study_time_day 6.0)
)

(make-instance 
	mauricio_teacher of teacher 
	(per_name "Mauricio" "Pepe")
	(per_lastname "Ramon" "Chavez")
	(per_age 45)
	(tea_investigation_time 6.0)
)

(make-instance 
	dsarmiento_student of student 
	(person-name "Douglas" "Bryan")
	(person-lastname "Sarmiento" "Basurto")
	(age 20)
)

;Eliminar instancia
(unmake-instance [<instance>])

;Guardar instancias
(save-instances "file_path")

;Cargar instancias
(load-instances "file_path")

;Imprimir 
(instances)
(list-definstances)

;----------------------------------------------------------
; send print: imprimir todos los valores de las instancias
(send [czhizhpon_student] print)

; send get: recuperar valores.
(send [czhizhpon_student] get-age)
(send [czhizhpon_student] get-person-name)
(send [czhizhpon_student] get-person-lastname)

; send put: Establecer los valores de una instancia.
(send [czhizhpon_student] put-age 22)


; Recuperar valores multislot.
(nth$ 1 (send [czhizhpon_student] get-person-name))


;----------------------------------------------------------
; Reglas

(defrule print-age
	?person <- (object 	(is-a person)
						(person-name $?n)
						(age ?a))
=>
	(printout t "Name[" (nth$ 1 $?n) "] | Age[" ?a "]" crlf)
)
