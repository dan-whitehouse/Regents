![NERIC Logo](images/Neric_horizontal-blue_yellow.png)
# Regents Order Form

## Description
Spring and Tomcat web application connected to a MySQL backend that collects orders
for testing sheets and documents for multiple regents administrations.

##### Features
* Step-by-step guided form
* Ability to opt out for a testing administration
* Ability to print order invoices
* View order history and non-administrations
* Admin Section
    * Order statistics for the current regents administration
    * Manage user accounts
    * Create and modify administration templates
        * Create/modify documents
        * Create/modify exams
        * Create/modify print options
        * Create/modify scan options
    * Modify orders
    * Create/modify districts and schools
#### Future Features
* Email order form once completed
* Field validation before moving to next step of order form
* User password reset without remembering old password

##### Dependencies
* Java 8
* Spring 4.2.5
* Hibernate 4.3.8
* MySQL 5

## Change Log
###v1.0.10
* Changed language on Step 5 of form for reporting option.
    * "NERIC will load scores to L1 for all administrations."

###v1.0.9
* Orders
    * Maintain sort and not refresh when 'Complete' box selected.
    * Added ability to filter.

###v1.0.8
* Added logic to prevent orders of number 0 in the order form.
* Removed Andrea's contact info from welcome page.
* Added Kristin's contact info to welcome page.
* Renamed "Testing and Evaluation Services" to "Testing and Data Reports" on welcome page.
