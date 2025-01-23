/**
 * @file helpers.go
 * @author Krisna Pranav
 * @brief Helpers
 * @version 1.0
 * @date 2024-01-23
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package utils

import (
	"strconv"
	"strings"
)

type FormFile struct {
	FileBytes    []byte
	FileName     string
	FormFileName string
}

func Atoi(in string) (int, error) {
	return strconv.Atoi(in)
}

func SelectHeaderAccept(accepts []string) string {
	if len(accepts) == 0 {
		return ""
	}

	if Contains(accepts, "application/json") {
		return "application/json"
	}

	return strings.Join(accepts, ",")
}

func Contains(haystack []string, needle string) bool {
	for _, a := range haystack {
		if strings.EqualFold(a, needle) {
			return true
		}
	}

	return false
}
