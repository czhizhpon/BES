
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
		(cardinality 4 4)
		(default -1 -1 -1 -1)
	)
	(multislot per_cat_result
		(type SYMBOL)
		(cardinality 4 4)
		(allowed-symbols l m h n)
		(default n n n n)
	)
	(multislot per_result_question
		(type INTEGER)
		(cardinality 22 22)
		(default -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1)
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

;--- Function: sum each category

(deffunction sum-category 
	(?cat ?sum $?v)
	
	(loop-for-count (?i 1 (length$ $?v)) do
		(bind ?aux (nth$ ?i ?v))
		(if (eq ?aux ?cat)
			then
				(bind ?sum (+ ?sum 1))
			else
				(bind ?sum ?sum)
		)
	)
	(bind ?sum ?sum)
)


;--- Rules for: emotional exhaustion

(defrule stu-cal-emotional-exhaustion
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_result_question $?rq))
	(test
		(eq (nth$ 1 $?r) -1)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	(bind $?res (send ?student get-per_result))

	;Add new result
	(bind ?ee (sum-emotional-exhaustion $?rq))
	(bind $?res (replace$ $?res 1 1 ?ee))
	
	(send ?student put-per_result $?res)

	;Print by console for test
	(bind $?res (send ?student get-per_result))
	(printout t "Resultado:" $?res " Resultado por pregunta:" $?rq crlf)
)

(defrule tea-cal-emotional-exhaustion
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_result_question $?rq))
	(test
		(eq (nth$ 1 $?r) -1)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	(bind $?res (send ?teacher get-per_result))

	;Add new result
	(bind ?ee (sum-emotional-exhaustion $?rq))
	(bind $?res (replace$ $?res 1 1 ?ee))
	
	(send ?teacher put-per_result $?res)
	
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
		(eq (nth$ 2 $?r) -1)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	(bind $?res (send ?student get-per_result))

	;Add new result
	(bind ?ee (sum-depersonalization $?rq))
	(bind $?res (replace$ $?res 2 2 ?ee))
	
	(send ?student put-per_result $?res)

	;Print by console for test
	(bind $?res (send ?student get-per_result))
	(printout t "Resultado:" $?res " Resultado por pregunta:" $?rq crlf)
)

(defrule tea-cal-depersonalization
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_result_question $?rq))
	(test
		(eq (nth$ 2 $?r) -1)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	(bind $?res (send ?teacher get-per_result))
	
	;Add new result
	(bind ?ee (sum-depersonalization $?rq))
	(bind $?res (replace$ $?res 2 2 ?ee))
	
	(send ?teacher put-per_result $?res)
	
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
		(eq (nth$ 3 $?r) -1)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	(bind $?res (send ?student get-per_result))

	;Add new result
	(bind ?ee (sum-personal-accomplishment $?rq))
	(bind $?res (replace$ $?res 3 3 ?ee))
	
	(send ?student put-per_result $?res)

	;Print by console for test
	(bind $?res (send ?student get-per_result))
	(printout t "Resultado:" $?res " Resultado por pregunta:" $?rq crlf)
)

(defrule tea-cal-personal-accomplishment
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_result_question $?rq))
	(test
		(eq (nth$ 3 $?r) -1)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	(bind $?res (send ?teacher get-per_result))
	
	;Add new result
	(bind ?ee (sum-personal-accomplishment $?rq))
	(bind $?res (replace$ $?res 3 3 ?ee))
	
	(send ?teacher put-per_result $?res)
	
	;Print by console for test
	(bind $?res (send ?teacher get-per_result))
	(printout t "Resultado:" $?res " Resultado por pregunta:" $?rq crlf)
)


;--- Rules for: total burnout

(defrule stu-cal-total-burnout
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_result_question $?rq))
	(test
		(eq (nth$ 4 $?r) -1)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	(bind $?res (send ?student get-per_result))
	(bind ?sum 0)

	; Add the total to the fourth position
	(loop-for-count (?i 1 (length$ $?res)) do
		(bind ?aux (nth$ ?i ?res))
		(if (= ?i (length$ $?res))
			then
				(bind $?res (replace$ $?res 4 4 ?sum))
			else
				(bind ?sum (+ ?sum ?aux))
		)
	)

	(send ?student put-per_result $?res)

	;Print by console for test
	(bind $?res (send ?student get-per_result))
	(printout t "Resultado:" $?res " Resultado por pregunta:" $?rq crlf)
)

(defrule tea-cal-total-burnout
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_result_question $?rq))
	(test
		(eq (nth$ 4 $?r) -1)
	)
=>

	(printout t "Resultado:" $?r " Resultado por pregunta:" $?rq crlf)
	(bind $?res (send ?teacher get-per_result))
	(bind ?sum 0)

	; Add the total to the fourth position
	(loop-for-count (?i 1 (length$ $?res)) do
		(bind ?aux (nth$ ?i ?res))
		(if (= ?i (length$ $?res))
			then
				(bind $?res (replace$ $?res 4 4 ?sum))
			else
				(bind ?sum (+ ?sum ?aux))
		)
	)

	(send ?teacher put-per_result $?res)

	;Print by console for test
	(bind $?res (send ?teacher get-per_result))
	(printout t "Resultado:" $?res " Resultado por pregunta:" $?rq crlf)
)

; --- Rules for: Students -> Categorize aspects

; -- Rules for: Emotional Exhaustion
(defrule stu-ee-low
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 1 $?cr) n)
			(>= (nth$ 1 $?r) 0)
			(<= (nth$ 1 $?r) 18)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?student get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 1 1 l))
	
	(send ?student put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?student get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

(defrule stu-ee-medium
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 1 $?cr) n)
			(>= (nth$ 1 $?r) 19)
			(<= (nth$ 1 $?r) 26)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?student get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 1 1 m))
	
	(send ?student put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?student get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

(defrule stu-ee-high
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 1 $?cr) n)
			(>= (nth$ 1 $?r) 27)
			(<= (nth$ 1 $?r) 54)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?student get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 1 1 h))
	
	(send ?student put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?student get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

; -- Rules for: Depersonalization
(defrule stu-dp-low
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 2 $?cr) n)
			(>= (nth$ 2 $?r) 0)
			(<= (nth$ 2 $?r) 5)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?student get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 2 2 l))
	
	(send ?student put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?student get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

(defrule stu-dp-medium
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 2 $?cr) n)
			(>= (nth$ 2 $?r) 6)
			(<= (nth$ 2 $?r) 9)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?student get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 2 2 m))
	
	(send ?student put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?student get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

(defrule stu-dp-high
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 2 $?cr) n)
			(>= (nth$ 2 $?r) 10)
			(<= (nth$ 2 $?r) 30)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?student get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 2 2 h))
	
	(send ?student put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?student get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

; -- Rules for: Personal Accomplishment
(defrule stu-pa-low
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 3 $?cr) n)
			(>= (nth$ 3 $?r) 0)
			(<= (nth$ 3 $?r) 33)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?student get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 3 3 l))
	
	(send ?student put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?student get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

(defrule stu-pa-medium
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 3 $?cr) n)
			(>= (nth$ 3 $?r) 34)
			(<= (nth$ 3 $?r) 39)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?student get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 3 3 m))
	
	(send ?student put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?student get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

(defrule stu-pa-high
	?student <- (object	(is-a student)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 3 $?cr) n)
			(>= (nth$ 3 $?r) 40)
			(<= (nth$ 3 $?r) 56)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?student get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 3 3 h))
	
	(send ?student put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?student get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)


; --- Rules for: Teachers -> Categorize aspects

; -- Rules for: Emotional Exhaustion
(defrule tea-ee-low
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 1 $?cr) n)
			(>= (nth$ 1 $?r) 0)
			(<= (nth$ 1 $?r) 18)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?teacher get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 1 1 l))
	
	(send ?teacher put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?teacher get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

(defrule tea-ee-medium
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 1 $?cr) n)
			(>= (nth$ 1 $?r) 19)
			(<= (nth$ 1 $?r) 26)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?teacher get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 1 1 m))
	
	(send ?teacher put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?teacher get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

(defrule tea-ee-high
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 1 $?cr) n)
			(>= (nth$ 1 $?r) 27)
			(<= (nth$ 1 $?r) 54)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?teacher get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 1 1 h))
	
	(send ?teacher put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?teacher get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

; -- Rules for: Depersonalization
(defrule tea-dp-low
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 2 $?cr) n)
			(>= (nth$ 2 $?r) 0)
			(<= (nth$ 2 $?r) 5)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?teacher get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 2 2 l))
	
	(send ?teacher put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?teacher get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

(defrule tea-dp-medium
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 2 $?cr) n)
			(>= (nth$ 2 $?r) 6)
			(<= (nth$ 2 $?r) 9)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?teacher get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 2 2 m))
	
	(send ?teacher put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?teacher get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

(defrule tea-dp-high
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 2 $?cr) n)
			(>= (nth$ 2 $?r) 10)
			(<= (nth$ 2 $?r) 30)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?teacher get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 2 2 h))
	
	(send ?teacher put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?teacher get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

; -- Rules for: Personal Accomplishment
(defrule tea-pa-low
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 3 $?cr) n)
			(>= (nth$ 3 $?r) 0)
			(<= (nth$ 3 $?r) 33)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?teacher get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 3 3 l))
	
	(send ?teacher put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?teacher get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

(defrule tea-pa-medium
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 3 $?cr) n)
			(>= (nth$ 3 $?r) 34)
			(<= (nth$ 3 $?r) 39)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?teacher get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 3 3 m))
	
	(send ?teacher put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?teacher get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

(defrule tea-pa-high
	?teacher <- (object	(is-a teacher)
						(per_result $?r)
						(per_cat_result $?cr))
	(test
		(and
			(eq (nth$ 3 $?cr) n)
			(>= (nth$ 3 $?r) 40)
			(<= (nth$ 3 $?r) 56)
		)
	)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?teacher get-per_cat_result))

	;Add new category
	(bind $?cat (replace$ $?cat 3 3 h))
	
	(send ?teacher put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?teacher get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

; --- Rules for: total burnout category

(defrule stu-bo-category
	?student <- (object	(is-a student)
							(per_result $?r)
							(per_cat_result $?cr))
		(test
			(eq (nth$ 4 $?cr) n)
		)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?student get-per_cat_result))

	;Add new category
	(bind ?sym n)

	(bind ?cantL (sum-category l 0 $?cat))
	(bind ?cantM (sum-category m 0 $?cat))
	(bind ?cantH (sum-category h 0 $?cat))

	(if (and
			(>= ?cantH ?cantM)
			(>= ?cantH ?cantL))
		then
			(bind ?sym h)
		else
			(if (>= ?cantM ?cantL)
				then
					(bind ?sym m)
				else
					(bind ?sym l)
			)
	)

	(if (and (eq (nth$ 1 $?cat) h)
			 (eq (nth$ 2 $?cat) h)
			 (eq (nth$ 3 $?cat) l))
		then
			(bind ?sym h)
		else
			(if (and (eq (nth$ 1 $?cat) l)
					 (eq (nth$ 2 $?cat) l)
			 		 (eq (nth$ 3 $?cat) h))
				then
					(bind ?sym l)
				else
					(bind ?sym ?sym)
			)
	)
	
	(bind $?cat (replace$ $?cat 4 4 ?sym))
	
	(send ?student put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?student get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)

(defrule tea-bo-category
	?teacher <- (object	(is-a teacher)
							(per_result $?r)
							(per_cat_result $?cr))
		(test
			(eq (nth$ 4 $?cr) n)
		)
=>
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
	(bind $?cat (send ?teacher get-per_cat_result))

	;Add new category
	(bind ?sym n)

	(bind ?cantL (sum-category l 0 $?cat))
	(bind ?cantM (sum-category m 0 $?cat))
	(bind ?cantH (sum-category h 0 $?cat))

	(if (and
			(>= ?cantH ?cantM)
			(>= ?cantH ?cantL))
		then
			(bind ?sym h)
		else
			(if (>= ?cantM ?cantL)
				then
					(bind ?sym m)
				else
					(bind ?sym l)
			)
	)

	(if (and (eq (nth$ 1 $?cat) h)
			 (eq (nth$ 2 $?cat) h)
			 (eq (nth$ 3 $?cat) l))
		then
			(bind ?sym h)
		else
			(if (and (eq (nth$ 1 $?cat) l)
					 (eq (nth$ 2 $?cat) l)
			 		 (eq (nth$ 3 $?cat) h))
				then
					(bind ?sym l)
				else
					(bind ?sym ?sym)
			)
	)
	
	(bind $?cat (replace$ $?cat 4 4 ?sym))
	
	(send ?teacher put-per_cat_result $?cat)
	
	;Print by console for test
	(bind $?cat (send ?teacher get-per_cat_result))
	(printout t "Resultado:" $?r " Categorización:" $?cr crlf)
)


; --- Rules for: print some recommendation

(defglobal ?*bo_recomendation*)

(defrule stu-low-bo-recommendation
	?student <- (object	(is-a student)
							(per_cat_result $?cr))
		(test
			(eq (nth$ 4 $?cr) l)
		)
=>
	(bind ?*bo_recomendation* "Su nivel de Burnout es bajo. Recomendación: Tomarse las clases con calma, y tomar descansos entre deberes.")

	(printout t ?*bo_recomendation* crlf)

)

(defrule stu-med-bo-recommendation
	?student <- (object	(is-a student)
							(per_cat_result $?cr))
		(test
			(eq (nth$ 4 $?cr) m)
		)
=>
	(bind ?*bo_recomendation* "Su nivel de Burnout es medio. Recomendación: Considere realizar actividades recreativas y físicas con regularidad sin que afecte sus responsabilidades académicas.")

	(printout t ?*bo_recomendation* crlf)

)

(defrule stu-hig-bo-recommendation
	?student <- (object	(is-a student)
							(per_cat_result $?cr))
		(test
			(eq (nth$ 4 $?cr) h)
		)
=>
	(bind ?*bo_recomendation* "Su nivel de Burnout es alto. Recomendación: Hablar de lo que le preocupa con alguien de confianza; contactar con un especialista es necesario si no desea bajar su rendimiento académico.")

	(printout t ?*bo_recomendation* crlf)

)

(defrule tea-low-bo-recommendation
	?teacher <- (object	(is-a teacher)
							(per_cat_result $?cr))
		(test
			(eq (nth$ 4 $?cr) l)
		)
=>
	(bind ?*bo_recomendation* "Su nivel de Burnout es bajo. Recomendación: Considere tomar descansos cortos entre sus horas de trabajo.")

	(printout t ?*bo_recomendation* crlf)

)

(defrule tea-med-bo-recommendation
	?teacher <- (object	(is-a teacher)
							(per_cat_result $?cr))
		(test
			(eq (nth$ 4 $?cr) m)
		)
=>
	(bind ?*bo_recomendation* "Su nivel de Burnout es medio. Recomendación: Considere realizar actividades extracurriculares con regularidad, además de meditar de vez en cuando.")

	(printout t ?*bo_recomendation* crlf)

)

(defrule tea-hig-bo-recommendation
	?teacher <- (object	(is-a teacher)
							(per_cat_result $?cr))
		(test
			(eq (nth$ 4 $?cr) h)
		)
=>
	(bind ?*bo_recomendation* "Su nivel de Burnout es alto. Recomendación: Considere consultar con un especialista en su institución; si no se hace tratar podría perjudicar su vida profesional y personal.")

	(printout t ?*bo_recomendation* crlf)

)

