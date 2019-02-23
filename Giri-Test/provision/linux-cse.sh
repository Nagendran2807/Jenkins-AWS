#!/bin/bash
echo "* soft nofile 131072">>/etc/security/limits.conf
echo "* hard nofile 131072">>/etc/security/limits.conf
echo "* soft stack 32768">>/etc/security/limits.conf
echo "* hard stack 32768">>/etc/security/limits.conf
echo "* soft nproc 8192">>/etc/security/limits.conf
echo "* hard nproc 8192">>/etc/security/limits.conf