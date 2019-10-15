function [ output_args ] = algoname( input_args )
%ALGONAME Summary of this function goes here
%   Detailed explanation goes here
a = strsplit(input_args, '.');
    switch string(a(3))
    case 'aco' 
        output_args = 'ACO';
    case 'inv'
        output_args = '2OPT';
    case 'con' 
        output_args = 'concorde';
    case 'lkh'
        output_args = 'linkern';
    case 'lkh2'
        output_args = 'LKH2';
    otherwise
        warning('Unexpected algorithm type. No plot created.')
    end
end

